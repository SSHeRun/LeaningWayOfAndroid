
import java.io.Serializable;

/**
 * Created by liguiyang on 2018/5/8.
 */

public class Student implements Serializable{
    String stuName;
    String stuNo;
    String stuGender;
    String stuClass;
    String imagePath;

    @Override
    public String toString() {
        return "Student{" +
                "stuName='" + stuName + '\'' +
                ", stuNo='" + stuNo + '\'' +
                ", stuGender='" + stuGender + '\'' +
                ", stuClass='" + stuClass + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
