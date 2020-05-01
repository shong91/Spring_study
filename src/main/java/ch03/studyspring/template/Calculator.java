package ch03.studyspring.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public int calcSum(String filePath) throws IOException {
//        BufferedReaderCallback sumCallback = new BufferedReaderCallback() {
//            @Override
//            public int doSthWithReader(BufferedReader br) throws IOException {
//                int sum = 0;
//                String line = null;
//
//                while ((line = br.readLine()) != null) {
//                    sum += Integer.valueOf(line);
//                }
//                return sum;
//            }
//        };
//        return fileReadTemplate(filePath, sumCallback);
        LineCallback<Integer> sumCallback = new LineCallback<Integer>() {
            @Override
            public Integer doSthWithLine(String line, Integer value) {
                return value + Integer.valueOf(line);

            }

        };
        return lineReadTemplate(filePath, sumCallback, 0);
    }

    public int calcMultiply(String filePath) throws IOException {
//        BufferedReaderCallback mulCallback = new BufferedReaderCallback() {
//            @Override
//            public int doSthWithReader(BufferedReader br) throws IOException {
//                int multiply = 1;
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    multiply *= Integer.valueOf(line);
//                }
//                return multiply;
//            }
//        };
//        return fileReadTemplate(filePath, mulCallback);

        LineCallback<Integer> mulCallback = new LineCallback<Integer>() {
            @Override
            public Integer doSthWithLine(String line, Integer value) {
                return value * Integer.valueOf(line);

            }

        };
        return lineReadTemplate(filePath, mulCallback, 1);
    }

    // 템플릿: 콜백 오브젝트를 받아 적절한 시점에 시행한다.
    // 콜백이 돌려준 결과는 최종적으로 모든 처리를 마친 후에 다시 클라이언트에게 돌려준다.
    public int fileReadTemplate(String filePath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            int ret = callback.doSthWithReader(br);
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // 라인 템플릿: 파일의 각 라인과 현재까지 계산한 값 리턴
    public <T> T lineReadTemplate(String filePath, LineCallback<T> callback, T initValue) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filePath));
            T res = initValue;
            String line = null;

            while((line = br.readLine()) != null) {
                res = callback.doSthWithLine(line, res); // 콜백 메소드가 계산 : 콜백을 while 문 안에서 여러번 호출
            }
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
        }
    }

    // 문자열 연결 기능 콜백
    public String concatenate(String filePath) throws IOException {
        LineCallback<String> concatCallback = new LineCallback<String>() {
            @Override
            public String doSthWithLine(String line, String value) {
                return value + line;
            }
        };
        return lineReadTemplate(filePath, concatCallback, "");
    }
}
