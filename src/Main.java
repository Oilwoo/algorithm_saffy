// 1. 프로토타입 인터페이스 또는 추상 클래스 구현
class Prototype implements Cloneable {
    private String data;

    public Prototype(String data) {
        this.data = data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    // 객체 복제를 위해 clone() 오버라이딩 (얕은 복사)
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Prototype{data='" + data + "'}";
    }
}

// 2. 클라이언트 코드에서 프로토타입 복제 사용
public class Main {
    public static void main(String[] args) {
        try {
            // 원본 프로토타입 객체 생성
            Prototype original = new Prototype("원본 데이터");
            System.out.println("원본: " + original);

            // 프로토타입 복제를 통해 새로운 객체 생성
            Prototype copy = (Prototype) original.clone();
            System.out.println("복제본: " + copy);

            // 복제본의 데이터 변경 (서로 독립적임)
            copy.setData("복제 데이터");
            System.out.println("변경 후 원본: " + original);
            System.out.println("변경 후 복제본: " + copy);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
