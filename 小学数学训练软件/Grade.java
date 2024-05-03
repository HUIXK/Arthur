import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//此类用于生成不同年级的题目，可根据不同年级和要求生成相应的题目列表

public class Grade {
    @SuppressWarnings("unused")
    private int gradeLevel;

    public Grade(int gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public List<Question> generateQuestions(String type, int count) {
        List<Question> questions = new ArrayList<>();
        Random rand = new Random();

        // 示例生成逻辑，需要根据实际需求完善
        for (int i = 0; i < count; i++) {
            int a = rand.nextInt(10) + 1;  // 生成1到10的随机数
            int b = rand.nextInt(10) + 1;
            String text = a + " + " + b;
            int answer = a + b;
            questions.add(new Question(text, answer));
        }

        return questions;
    }
}

