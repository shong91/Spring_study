package ch03.studyspring.template;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback {
    int doSthWithReader(BufferedReader br) throws IOException;
}
