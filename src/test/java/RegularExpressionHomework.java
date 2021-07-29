import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressionHomework {
    public static void main(String[] args) {

        String address = "41 University Drive • Suite 202,\n Newtown, PA 18940 • USA";
        List<String> addressSplitted = Arrays.asList(address.split("\n"));

        String streetNameAndAppartmentsNumber = addressSplitted.get(0);
        String townStateZipCountry = addressSplitted.get(1);
        String streetNumber = getValueByRegEx(streetNameAndAppartmentsNumber, "(\\d+)(\\s\\w+\\s?\\w*\\s?)",1).trim();
        String streetName = getValueByRegEx(streetNameAndAppartmentsNumber, "(\\d+)(\\s\\w+\\s?\\w*\\s?)",2).trim();
        String officeNumber = getValueByRegEx(streetNameAndAppartmentsNumber, "(\\d+\\s)(\\w+\\s?\\w*\\s?)(.?\\s\\w+)(\\s\\d+)",4).trim();
        String city = getValueByRegEx(townStateZipCountry, "(\\w+\\s?\\w*)(.)(\\s[A-Z]{2}\\s)",1).trim();
        String state = getValueByRegEx(townStateZipCountry, "(\\w+\\s?\\w*)(.)(\\s[A-Z]{2}\\s)",3).trim();
        String zipCode = getValueByRegEx(townStateZipCountry, "(\\w+\\s?\\w*)(.)(\\s[A-Z]{2}\\s)(\\d{5})",4);
        String country = getValueByRegEx(townStateZipCountry, "(\\w+\\s?\\w*)(.)(\\s[A-Z]{2}\\s)(\\d{5})(\\s.?\\s)(\\w+)",6).trim();
        System.out.println("Street name: " + streetName);
        System.out.println("Street number: " + streetNumber);
        System.out.println("Office number: " + officeNumber);
        System.out.println("City: " + city);
        System.out.println("State: " + state);
        System.out.println("Zip code: " + zipCode);
        System.out.println("Country: " + country);

// 2.	Write regular expression to validate email, IP, credit card
        System.out.println(isStringMatchesRegex("adsd.332@fsdf.ru","^(\\w+(\\.|-|\\+)?\\w+){2,64}@(\\w+\\.?-?\\w+){2,253}\\.[a-z]{2,64}$"));
        System.out.println(isStringMatchesRegex("anndsn_dsds@msi-reg.com","^(\\w+(\\.|-|\\+)?\\w+){2,64}@(\\w+\\.?-?\\w+){2,253}\\.[a-z]{2,64}$"));
        System.out.println(isStringMatchesRegex("tuba@gmail.","^(\\w+(\\.|-|\\+)?\\w+){2,64}@(\\w+\\.?-?\\w+){2,253}\\.[a-z]{2,64}$"));
        System.out.println(isStringMatchesRegex("192.168.0.1","^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"));
        System.out.println(isStringMatchesRegex("254.254.254.0","^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"));
        System.out.println(isStringMatchesRegex("1.1.1.0","^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$"));
        System.out.println(isStringMatchesRegex("4564 9863 9452 7521","^((\\d{4})\\s?){3}((\\d{4})\\s?)$"));
        System.out.println(isStringMatchesRegex("1234567812345678","^((\\d{4})\\s?){3}((\\d{4})\\s?)$"));
        System.out.println(isStringMatchesRegex("123456781234567","^((\\d{4})\\s?){3}((\\d{4})\\s?)$"));

    }

    public static boolean isStringMatchesRegex (String myText, String regularExpression){
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(myText);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static String getValueByRegEx (String myText, String regularExpression, int groupNumber){
        String text = "";
        Pattern pattern = Pattern.compile(regularExpression);
        Matcher matcher = pattern.matcher(myText);
        if (matcher.find()) {
            text = matcher.group(groupNumber);
        }
        return text;
    }
}
