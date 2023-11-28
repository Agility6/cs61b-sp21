package gh2;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import deque.ArrayDeque;

public class GuitarHero {

    public static void main(String[] args) {

        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        ArrayDeque<GuitarString> keyArray = new ArrayDeque<>();
        double sound;

        for (int i = 0; i < keyboard.length(); i++) {
            sound = 440 * Math.pow(2, (i - 24) / 12.0);
            GuitarString guitarString = new GuitarString(sound);
            keyArray.addLast(guitarString);
        }

        while (true) {

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int ind = keyboard.indexOf(key);
                if (ind >= 0 && ind < 37) {
                    keyArray.get(ind).pluck();
                }
            }

            double sample = 0.0;
            for (int i = 0; i < keyboard.length(); i += 1) {
                sample += keyArray.get(i).sample();
            }

            StdAudio.play(sample);

            for (int i = 0; i < keyboard.length(); i += 1) {
                keyArray.get(i).tic();
            }
        }

    }
}