package functional;

public class InsuranceFormJson {

    public static String getInsuranceFormJson(String firstName, String lastName, String gender, String address, String dob,
                                              String dlNumber, String dlExp, String effDate) {
        return "Firstname="+firstName
                +"&Lastname="+lastName
                +"&Address="+address
                +"&gender="+gender
                +"&Birthdate="+dob
                +"&dlNumber="+dlNumber
                +"&dlExpiration="+dlExp
                +"&policyEffectiveDate="+effDate;
    }
}
