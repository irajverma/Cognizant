interface Playable {
    void play();
}

class Guitar implements Playable {
    @Override
    public void play() {
        System.out.println("Strumming the guitar chords...");
    }
}

class Piano implements Playable {
    @Override
    public void play() {
        System.out.println("Playing a classical melody on the piano keys...");
    }
}

public class Problem_19_InterfaceImplementation {
    public static void main(String[] args) {
        Playable guitar = new Guitar();
        Playable piano = new Piano();
        
        guitar.play();
        piano.play();
    }
}