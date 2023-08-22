
public class Main {
    public static String calc(String input) throws WrongInputDataExeption, WrongMathExpressionExeption {
        try {
            String result = "";
            String formattedString = formedStr(input);
            String[] s = formattedString.split(" ");

            if (isRoman(s[0]) && isRoman(s[2])) {
                int number1 = RomanNumber.valueOf(s[0]).getValue();
                int number2 = RomanNumber.valueOf(s[2]).getValue();
                int romanResult = calculate(number1, number2, s[1]);
                if (romanResult < 0) {
                    return "throws Exception //т.к. в римской системе нет отрицательных чисел";
                }
                result = toRoman(romanResult);
            } else if (isArabian(s[0]) && isArabian(s[2])) {
                int number1 = Integer.parseInt(s[0]);
                int number2 = Integer.parseInt(s[2]);
                if (number1 > 10 || number2 > 10) {
                    return "throws Exception //т.к. Калькулятор должен принимать на вход числа от 1 до 10 " +
                            "включительно, не более";
                }
                Integer arabianResult = (Integer) calculate(number1, number2, s[1]);
                result = arabianResult.toString();
            } else {
                return "throws Exception //т.к. используются одновременно разные системы счисления";
            }
            return result;

        }catch (WrongMathExpressionExeption e) {
            return "throws Exception //т.к. строка не является математической операцией";
        }catch (WrongInputDataExeption e){
            return "throws Exception //т.к. формат математической операции не удовлетворяет" +
                    " заданию - два операнда и один оператор (+, -, /, *)";
        }
    }

    static String toRoman(int romanResult) {
        if (romanResult == 0)
            return "nulla";
        else {
            int value = romanResult % 10;
            return toRoman10_100(romanResult - value) + toRoman1_9(value);
        }
    }
    static String toRoman10_100(int i) {
        switch (i) {
            case 0:
                return "";
            case 10:
                return "X";
            case 20:
                return "XX";
            case 30:
                return "XXX";
            case 40:
                return "XL";
            case 50:
                return "L";
            case 60:
                return "LX";
            case 70:
                return "LXX";
            case 80:
                return "LXXX";
            case 90:
                return "XC";
            case 100:
                return "C";
        }
        return null;
    }
    static String toRoman1_9(int value) {
        switch (value){
            case 0:
                return "";
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
            case 5:
                return "V";
            case 6:
                return "VI";
            case 7:
                return "VII";
            case 8:
                return "VIII";
            case 9:
                return "IX";
        }
        return null;
    }
    static int calculate(int number1, int number2, String s) {
        switch (s){
            case "+":
                return number1 + number2;
            case "-":
                return number1 - number2;
            case "*":
                return number1 * number2;
            case "/":
                return (number1 / number2);
        }
        return number1;
    }
    static boolean isArabian(String str){
        return str.matches("\\d+");
    }
    static boolean isRoman(String str){
        try {
            RomanNumber number = RomanNumber.valueOf(str);
            return true;
        } catch (IllegalArgumentException e){
            return false;
        }
    }
    static String formedStr(String input) throws WrongMathExpressionExeption, WrongInputDataExeption {
        String[] split;
        String result;
        String sign;
        if(input.contains("+")){
            split = input.split("\\+");
            sign = "+";
        }else if (input.contains("-")){
            split = input.split("-");
            sign = "-";
        }else if (input.contains("*")){
            split = input.split("\\*");
            sign = "*";
        }else if (input.contains("/")){
            split = input.split("/");
            sign = "/";
        } else {
            throw new WrongMathExpressionExeption();
        }
        if (split.length>2){
            throw new WrongInputDataExeption();
        } else {
            result = deleteExtraProbel(split[0]) + sign + " " + deleteExtraProbel(split[1]);
            return result.substring(0, result.length()-1);
        }
    }
    static String deleteExtraProbel(String str) {
        String[] s = str.split(" ");
        String result = null;
        for (String s1 : s) {
            if (s1.length() != 0){
                result = s1 + " ";
            }
        }
        return result;
    }
    enum RomanNumber {
        I(1), II(2), III(3), IV(4), V(5), VI(6), VII(7), VIII(8),
        IX(9), X(10);

        private int value;

        RomanNumber(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
class WrongMathExpressionExeption extends Exception{
}
class WrongInputDataExeption extends Exception{
}
