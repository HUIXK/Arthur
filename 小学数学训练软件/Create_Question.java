import java.util.Random;

/**
 * 问题生成器类，用于根据指定的年级和题型生成数学问题。
 */
public class Create_Question {
    private static final Random rand = new Random();  // 随机数生成器

    //根据年级和题型生成一个数学问题。
    //param grade 年级（"一年级", "二年级", "三年级"）
    //param type 题型（"加法", "减法", "乘法", "除法"）
    //return 生成的数学问题。

    public static Question generateQuestion(String grade, String type) {
        int num1, num2, answer;  // 用于存储数字和答案
        String text;             // 问题文本

        switch (grade) {
            case "一年级":
                num1 = rand.nextInt(10);  // 生成0到9之间的随机数
                num2 = rand.nextInt(10);  // 同上
                if (type.equals("加法")) {
                    answer = num1 + num2;
                    text = num1 + " + " + num2;
                } else {  // 减法
                    if (num1 < num2) {
                        int temp = num1;  // 保证不出现负数结果
                        num1 = num2;
                        num2 = temp;
                    }
                    answer = num1 - num2;
                    text = num1 + " - " + num2;
                }
                break;
            case "二年级":
                num1 = rand.nextInt(100);  // 生成0到99之间的随机数
                num2 = rand.nextInt(100);  // 同上
                if (type.equals("加法")) {
                    answer = num1 + num2;
                    text = num1 + " + " + num2;
                } else {  // 减法
                    if (num1 < num2) {
                        int temp = num1;  // 同上，避免负数
                        num1 = num2;
                        num2 = temp;
                    }
                    answer = num1 - num2;
                    text = num1 + " - " + num2;
                }
                break;
            default:  // 三年级，目前仅包括乘法和除法
                num1 = rand.nextInt(10) + 1;  // 生成1到10之间的随机数
                num2 = rand.nextInt(10) + 1;  // 同上
                if (type.equals("乘法")) {
                    answer = num1 * num2;
                    text = num1 + " * " + num2;
                } else {  // 除法
                    answer = num1;  // 存储除数
                    num1 *= num2;   // 保证被除数是乘数的倍数，避免小数结果
                    text = num1 + " / " + num2;
                }
                break;
        }
        return new Question(text, answer);  // 创建并返回一个新的问题对象
    }
}


