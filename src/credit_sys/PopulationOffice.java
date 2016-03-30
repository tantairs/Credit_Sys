package credit_sys;

public class PopulationOffice {

    public static boolean addrEquals(String firstAddr, String lastAddr) {
        if (firstAddr.contains(lastAddr) || lastAddr.contains(firstAddr)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean phoneEquals(String phone, String _phone) {
        return (phone.equals(_phone));
    }

    public static boolean workplaceEquals(String workplace, String _workplace) {
        return (workplace.equals(_workplace));
    }

    public static String parseCusType(String type) {
        switch (type) {
            case "本地户籍":
                return "0";
            case "本地居住":
                return "1";
            case "其他":
                return "3";
            default:
                return "2";
        }
    }
}
