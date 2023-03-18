import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


public class Test {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // 전체 인원 입력
        System.out.print("전체 인원을 입력: ");
        int person = -1;
        while (person == -1) {
            person = isNumber(bf.readLine());
        }
//        System.out.println(person);

        // 청소할 인원을 입력
        System.out.printf("청소할 인원을 입력(%d명 이하): ", person);
        int cleanPerson = -1;
        while (cleanPerson == -1) {
            cleanPerson = isNumber(bf.readLine());
            if (cleanPerson != -1 && cleanPerson > person){
                System.out.println("청소 인원은 전체 인원을 넘을 수 없습니다.");
                cleanPerson = -1;
            }
        }
//        System.out.println(cleanPerson);

        // 청소할 날짜 입력
        System.out.print("청소할 날짜를 입력 1: 한주 전체, 2: 당일 ");
        int clsDays = -1;
        while (clsDays == -1) {
            clsDays = isNumber(bf.readLine());
            if (!(clsDays == 1 || clsDays == 2)){
                System.out.println("1, 2 둘중 하나를 입력해 주세요.");
                clsDays = -1;
            }
        }
//        System.out.println(clsDays);

        // 무작위 번호 추출
        int[] cleanNumArr = new int[cleanPerson];
        Random rn = new Random();
        HashMap<Integer, Boolean> hm = new HashMap<>();
        for (int i = 0; i < cleanPerson; i++) {
            int num = rn.nextInt(23) + 1;
            if (hm.getOrDefault(num, true)) {
                cleanNumArr[i] = num;
                hm.put(num, false);
            } else i--;
        }

        // 정렬
        Arrays.sort(cleanNumArr);

        // 출력
        System.out.printf("전체 인원: %d명 \n청소 인원: %d명 \n청소 당번:[ ", person, cleanPerson);
        for (int i : cleanNumArr) System.out.print(i + " ");
        System.out.printf("] \n청소 반장: %d번\n", cleanNumArr[rn.nextInt(cleanPerson)]);
        System.out.println((clsDays == 1 ? "이번주 한주 동안 " : "오늘 당일 ") + "청소입니다.");


    }

    private static int isNumber(String isNumber) {
        char[] cArr = isNumber.toCharArray();
        if (isNumber.length() == 1 && isNumber.charAt(0) == 45) {
            System.out.println("숫자를 입력해 주세요");
            return -1;
        }
        for (int i = 0; i < cArr.length; i++) {
            if (i == 0 && cArr[i] == 45) continue;
            if (!(cArr[i] <= 57 && 48 <= cArr[i])) {
                System.out.println("숫자를 입력해 주세요");
                return -1;
            }
        }
        int result = Integer.parseInt(isNumber);
        if (!(result <= 23 && result >= 1)) {
            System.out.println("유효한 범위의 숫자를 입력해 주세요");
            return -1;
        }
        return result;
    }

}


/*

1. 청소 대상 전체 인원 설정
2. 청소 추출 인원 선택(3, 5)
3. 한 주(월~금) 인원 일괄 및 당일
4. 무작위 추출
5. 같은 번호 나올 시 재추출
6. 선정된 인원은 올림차순 정렬
7. 추출된 인원에서 청소 반장 선정

 */