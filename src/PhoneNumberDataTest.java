import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberDataTest {

    @org.junit.jupiter.api.Test
    void getPhoneNumber() {
        PhoneNumberData pnd = new PhoneNumberData("0749091217");
        String expected = pnd.getPhoneNumber();
        String actual = "0722683155";
        Assertions.assertEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void setPhoneNumber() {
        PhoneNumberData pnd = new PhoneNumberData("0749091217");
        pnd.phoneNumber = "0771519112";
        pnd.setPhoneNumber(pnd.phoneNumber);
        Assertions.assertEquals(pnd.getPhoneNumber(), pnd.phoneNumber);
    }
}