
/**
 * task_2
 */
import java.io.*;

public class task_2 {

    public static void main(String[] args) {
        String incoming_string = "TEST_MY_STRING";
        String find_string = "TSET";
        Integer A = 3;
        Integer B = 56;
        String input_string = null;
        String rotation_string = null;
        StringBuilder equality_through_insert = null;
        StringBuilder equality_through_replace = null;
        if (input(incoming_string, find_string) != 0) {
            input_string = "Есть вхождение строки";
            System.out.println("Есть вхождение строки");
        } else {
            input_string = "Нет вхождения строки";
            System.out.println("Нет вхождения строки");
        }
        rotation_string = rotation(incoming_string, find_string);
        char[] c = incoming_string.toCharArray();
        Raotation_with_recursion(c, 0, c.length - 1);
        String reversed_string = new String(c);
        System.out.print("Перевернутая строка с помощью рекурсии " + reversed_string);
        StringBuilder Result_numetric = Nubers_conversion(A, B);
        String res = Result_numetric.toString();
        equality_through_insert = equality_through_insert(Result_numetric);
        equality_through_replace = equality_through_replace(Result_numetric);
        String time_1 = replace_whith_String(build_string());
        String time_2 = replace_whith_StringBuilder(build_string());
        ;
        write_to_file(incoming_string, find_string, A, B, input_string, rotation_string, reversed_string,
                res,
                equality_through_insert, equality_through_replace, time_1, time_2);

    }

    /* Проверяет, является ли строка пустой ("") или нулевой. */
    public static boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }

    public static int input(String incoming_string, String find_string) {
        {
            if (isEmpty(incoming_string) || isEmpty(find_string)) {
                return 0;
            }

            return incoming_string.split(find_string, -1).length - 1;
        }
    }

    public static String rotation(String incoming_string, String find_string) {
        char tmp_char_inc;
        char tmp_char_find;
        String rotation_string = null;
        if (incoming_string.length() != find_string.length()) {
            rotation_string = "Строки не являются вращением";
            System.out.println("Строки не являются вращением");
            return rotation_string;
        }
        for (int i = 0; i < incoming_string.length(); i++) {
            tmp_char_inc = incoming_string.charAt(i);
            tmp_char_find = find_string.charAt(find_string.length() - 1 - i);
            if (tmp_char_inc != tmp_char_find) {
                System.out.println("Строки не являются вращением");
                rotation_string = "Строки не являются вращением";
                return rotation_string;
            }
        }
        rotation_string = "Строки являются вращением";
        System.out.println("Строки являются вращением");
        return rotation_string;
    }

    private static void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    public static void Raotation_with_recursion(char[] c, int l, int h) {
        if (l < h) {
            swap(c, l, h);
            Raotation_with_recursion(c, l + 1, h - 1);
        }
    }

    public static StringBuilder Nubers_conversion(Integer A, Integer B) {
        StringBuilder sb = new StringBuilder();
        Integer C = A + B;
        Integer D = A - B;
        Integer E = A * B;
        String add = "+";
        String multiply = "*";
        String subtraction = "-";
        String equality = "=";
        sb.append(Integer.toString(A));
        sb.append(add);
        sb.append(Integer.toString(B));
        sb.append(equality);
        sb.append(Integer.toString(C));
        sb.append(' ');
        sb.append(Integer.toString(A));
        sb.append(subtraction);
        sb.append(Integer.toString(B));
        sb.append(equality);
        sb.append(Integer.toString(D));
        sb.append(' ');
        sb.append(Integer.toString(A));
        sb.append(multiply);
        sb.append(Integer.toString(B));
        sb.append(equality);
        sb.append(Integer.toString(E));
        System.out.println(sb);
        return sb;
    }

    public static StringBuilder equality_through_insert(StringBuilder converse_string) {

        for (int i = 0; i < converse_string.length(); i++) {
            if (converse_string.charAt(i) == '=') {
                converse_string.deleteCharAt(i);
                converse_string.insert(i, " равно ");
            }
        }
        System.out.println(converse_string);
        return converse_string;
    }

    public static StringBuilder equality_through_replace(StringBuilder converse_string) {

        for (int i = 0; i < converse_string.length(); i++) {
            if (converse_string.charAt(i) == '=') {
                converse_string.replace(i, i + 7, " равно ");
            }
        }
        System.out.println(converse_string);
        return converse_string;
    }

    public static String build_string() {
        String build_string = "";
        for (int i = 0; i < 1000; i++) {
            build_string = build_string.concat("=");
        }
        return build_string;
    }

    public static String replace_whith_String(String test_string) {
        long time = System.nanoTime();
        String answer_string = test_string.replace("=", "равно"); // решил не возвращать строки
        time = System.nanoTime() - time;
        String time_1 = "Выполнено за " + time / 1_000_000.0 + " мс";
        return time_1;
    }

    public static String replace_whith_StringBuilder(String test_string) {
        StringBuilder sb = new StringBuilder(test_string);
        long time = System.nanoTime();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '=') {
                sb.replace(i, i + 5, "равно");
            }
        }
        time = System.nanoTime() - time;
        String time_2 = "Выполнено за " + time / 1_000_000.0 + " мс";

        return time_2;
    }

    public static void write_to_file(String incoming_String, String find_string,
            Integer A, Integer B, String input_string,
            String rotation_string, String reversed_string, String res,
            StringBuilder equality_through_insert, StringBuilder equality_through_replace, String time_1,
            String time_2) {

        try (FileWriter writer = new FileWriter("result.txt", false)) {

            StringBuilder text_8_b = new StringBuilder("Замена на слово равно ");
            StringBuilder text_9_b = new StringBuilder("Замена на слово равно второй способ ");
            String text_1 = "исходная строка =" + incoming_String + '\n';
            String text_2 = "поисковая строка =" + find_string + '\n';
            String text_3 = "число А = " + A + '\n';
            String text_4 = "число B  =" + B + '\n';
            String text_10 = "Есть ли вхождение? " + input_string + '\n';
            String text_5 = "Являются ли вращением? " + rotation_string + '\n';
            String text_6 = "перевернутая строка через рекурсию  =" + reversed_string + '\n';

            String text_7 = "Результируящая строка : " + res + '\n';
            text_8_b = text_8_b.append(equality_through_insert);
            String text_8 = text_8_b.toString() + '\n';
            text_9_b = text_9_b.append(equality_through_replace);
            String text_9 = text_9_b.toString() + '\n';
            String text_11 = "время замены через String " + time_1 + '\n';
            String text_12 = "время замены через StringBuilder " + time_2 + '\n';
            writer.write(text_1);
            writer.write(text_2);
            writer.write(text_3);
            writer.write(text_4);
            writer.write(text_10);
            writer.write(text_5);
            writer.write(text_6);
            writer.write(text_7);
            writer.write(text_8);
            writer.write(text_9);
            writer.write(text_11);
            writer.write(text_12);
            writer.flush();
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }
}
