/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RecognizeVoice;

import edu.cmu.sphinx.api.AbstractSpeechRecognizer;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.frontend.util.StreamDataSource;
import java.io.IOException;

/**
 *
 * @author quochung
 */
public class LiveSpeechRecognizerExtention extends AbstractSpeechRecognizer {

    private static final MircrophoneExtension microphone = new MircrophoneExtension(16000, 16, true, false);

    public LiveSpeechRecognizerExtention(Configuration configuration)
            throws IOException {
        super(configuration);             
        ((StreamDataSource) context.getInstance(StreamDataSource.class)).setInputStream(microphone.getStream());
    }

    public void startRecognition(boolean clear) {
        recognizer.allocate();
        microphone.startRecording();
    }

    public void stopRecognition() {
        microphone.stopRecording();
        recognizer.deallocate();
    }

    public void closeRecognitionLine() {
        microphone.closeLine();
    }
}
