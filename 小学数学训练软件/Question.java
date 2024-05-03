import java.io.Serializable;

/**
 * 这个类表示一个数学问题。
 * 它包括问题的文本和正确答案。
 * 实现 Serializable 接口是为了允许问题对象被保存到文件或从文件中恢复。
 */
public class Question implements Serializable {
    private static final long serialVersionUID = 1L;  

    private String text;  // 问题的文本
    private int answer;   // 问题的正确答案

    public Question(String text, int answer) {
        this.text = text;
        this.answer = answer;
    }

    //返回问题的文本。
    public String getText() {
        return text;
    }

    //返回问题的正确答案。
    public int getAnswer() {
        return answer;
    }
}



