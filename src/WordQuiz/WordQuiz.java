package WordQuiz;

import java.util.*;

public class WordQuiz {
    public static void main(String[] args) {
        List<Word> allWords = new ArrayList<>(List.of(
                new Word("apple", "사과"),
                new Word("computer", "컴퓨터"),
                new Word("school", "학교"),
                new Word("book", "책"),
                new Word("coffee", "커피"),
                new Word("mountain", "산"),
                new Word("river", "강"),
                new Word("pencil", "연필"),
                new Word("car", "자동차"),
                new Word("music", "음악"),
                new Word("phone", "전화기"),
                new Word("dog", "강아지")
        ));

        Scanner scanner = new Scanner(System.in);

        int numQuestions = 0;

        while (true) {
            System.out.println("🔤 영어 단어 퀴즈를 시작");
            System.out.println("전체 " + allWords.size() + "개 중 몇 문제를 풀겠습니까?");
            System.out.print("문제 수 입력: ");
            numQuestions = scanner.nextInt();
            scanner.nextLine(); // 개행 제거

            if (numQuestions > 0 && numQuestions <= allWords.size()) {
                break;
            } else {
                System.out.println("❗ 유효하지 않은 문제 수입니다. 다시 입력해주세요.\n");
            }
        }
        Collections.shuffle(allWords);
        List<Word> quizList = allWords.subList(0, numQuestions);
        List<Word> wrongList = new ArrayList<>();

        playQuiz(quizList, scanner, wrongList);

        while (!wrongList.isEmpty()) {
            System.out.println("\n🔁 틀린 문제를 다시 풀고 싶나요?");
            System.out.println("1. 다시 풀기");
            System.out.println("2. 종료하기");
            System.out.print("선택: ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                List<Word> retryList = new ArrayList<>(wrongList);
                wrongList.clear();
                playQuiz(retryList, scanner, wrongList);
            } else {
                break;
            }
        }

        System.out.println("\n👋 퀴즈를 종료합니다. 수고하셨습니다!");
        scanner.close();
    }

    public static void playQuiz(List<Word> quizList, Scanner scanner, List<Word> wrongList) {
        int score = 0;
        System.out.println("\n📝 퀴즈 시작!\n");

        for (int i = 0; i < quizList.size(); i++) {
            Word word = quizList.get(i);
            System.out.print((i + 1) + ". " + word.getKorean() + " → 영어로? ");
            String answer = scanner.nextLine();

            if (answer.trim().equalsIgnoreCase(word.getEnglish())) {
                System.out.println("✅ 정답입니다!\n");
                score++;
            } else {
                String english = word.getEnglish();
                String hint = english.charAt(0) + "*".repeat(english.length() - 1);

                System.out.println("❌ 오답입니다!");
                System.out.println("힌트: " + hint + "\n");

                wrongList.add(word);
            }
        }

        System.out.println("총 " + quizList.size() + "문제 중 " + score + "개 맞췄습니다.");
        System.out.println("👉 현재 점수: " + (score * 1) + "점");
    }
}
