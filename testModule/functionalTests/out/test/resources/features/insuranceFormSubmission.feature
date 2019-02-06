Feature: verify auto insurance policy form submission for valid and invalid details

  Background:
    Given I access auto insurance policy form page

  @form @success
  Scenario Outline: verify auto insurance policy form submission with valid details
    When I enter "<firstName>", "<lastName>", "<address>" and select gender as "<gender>"
    And I enter "<dob>"
    And I select driving license number as "<dLNumber>" and expiry date as "<dLExpDate>"
    And I select training certification status as "<TCStatus>"
    And I select effective date as "<effectDate>"
    And I click on submit
    Then I should see a success message
#    And details as dob "<dob>", DL details as "<dLNumber>" and "<dLExpDate>", TCStatus as "<TCStatus>" and effective date as "<days>" days in "<futureOrPast>"

    Examples:
      | firstName | lastName | address | gender   | dob         | dLNumber | dLExpDate  | TCStatus | effectDate |
      | abc       | def      | address | male     | 01/01/2002  | D1234567 | 01/24/2019 | invalid  | 01/24/2019 |
      | abc       | def      | address | female   | 12/12/2002  | D1234567 | 01/24/2019 | invalid  | 12/24/2018 |
      | abc       | def      | address | female   | 12/12/2002  | D1234567 | 01/24/2019 | valid    | 01/22/2019 |
      | abc       | def      | address | male     | 12/12/1964  | D1234567 | 01/24/2019 | invalid  | 02/22/2019 |
      | abc       | def      | address | male     | 12/12/1963  | D1234567 | 01/24/2019 | valid    | 02/22/2019 |
      |           |          |         | male     | 12/31/2002  | D1234567 | 01/24/2019 | invalid  |            |


  @form @error
  Scenario Outline: verify auto insurance policy form submission with valid details
    When I enter "<firstName>", "<lastName>", "<address>" and select gender as "<gender>"
    And I enter "<dob>"
    And I select driving license number as "<dLNumber>" and expiry date as "<dLExpDate>"
    And I select training certification status as "<TCStatus>"
    And I select effective date as "<effectDate>"
    And I click on submit
    Then I should see a error message as "<errorMessage>"

    Examples:
      | firstName | lastName | address | gender   | dob         | dLNumber | dLExpDate  | TCStatus | effectDate | errorMessage |
      | abc       | def      | address | male     | 12/31/2020  | D1234567 | 01/24/2019 | invalid  | 01/24/2019 | Error: driver must be at least 16           |
      | abc       | def      | address | female   | 12/31/1900  | D1234567 | 01/24/2019 | valid    | 12/24/2018 | Error: driver must be less than 110 yearsa old            |
      | abc       | def      | address | male     | 12/31/1963  | D1234567 | 01/24/2019 | invalid  | 02/22/2019 | Error: drivers over 55 must complete a training course             |
      | abc       | def      | address | male     | 12/31/2004  | D1234567 | 01/24/2019 | valid    | 01/23/2019 | Error: driver must be at least 16            |
      | abc       | def      | address | male     | 12/31/1908  | D1234567 | 01/24/2019 | valid    | 02/22/2019 | Error: driver must be less than 110 yearsa old            |
      | abc       | def      | address | male     | 12/31/2002  | D1234567 | 01/23/2019 | valid    | 02/22/2019 | Error: driver must have a valid license           |
      | abc       | def      | address | male     | 12/31/2002  | D1234567 | 01/22/2019 | valid    | 02/22/2019 | Error: driver must have a valid license              |
      | abc       | def      | address | male     | 12/31/2002  |          | 01/24/2019 | valid    | 02/22/2019 | Error: customer must have a license              |
      | abc       | def      | address | male     | 12/31/2002  | D1234567 | 01/24/2019 | valid    | 02/24/2019 | Error: policy effective date can not be more than 45 days in the future             |
      | abc       | def      | address | male     | 12/31/2002  | D1234567 | 01/24/2019 | valid    | 12/23/2018 | Error: policy effective date can not be more than 30 days in the past            |









#  @form @error
#  Scenario Outline: verify auto insurance policy form submission error with invalid details
#    When I enter age as "<age>"
#    And I select driving license status as "<DLStatus>"
#    And If <age> is more than 55, I enter training certification status as "<TCStatus>"
#    And I select effective date as <days> in "<futureOrPast>"
#    And I click on submit
#    Then I should see a error message as "<errorMessage>"
#    And "<field>" should be highlighted in red
#
#    Examples:
#      | age | DLStatus | TCStatus | days | futureOrPast | errorMessage | field         |
#      | 17  | invalid  | NA       | 45   | future       |              | DLStatus      |
#      | 56  | valid    | invalid  | 45   | future       |              | TCStatus      |
#      | 56  | valid    | NA       | 45   | future       |              | TCStatus      |
##      | 0   | valid    | NA       | 45   | future       |              | age           |
#      | 16  | valid    | NA       | 45   | future       |              | age           |
#      | 999 | valid    | NA       | 45   | future       |              | age           |
#      | 110 | valid    | valid    | 30   | past         |              | age           |
#      | 56  | valid    | valid    | 46   | future       |              | effectiveDate |
#      | 109 | valid    | valid    | 45   | past         |              | effectiveDate |
#      | 56  | invalid  | invalid  | 45   | future       |              | DLStatus      |
#      | 56  | invalid  | invalid  | 45   | future       |              | TCStatus      |