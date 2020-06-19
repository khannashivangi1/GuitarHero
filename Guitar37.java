// This class implements Guitar interface to create Guitar37 object.
// Keeps track of the number of tics and returns the samples for all 
// the valid keynotes. 

public class Guitar37 implements Guitar {
    public static final String KEYBOARD =
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    private GuitarString[] guitarString37;
    private int tic;
    
    // Constructs an array of GuitarString objects and assigns a unique 
    // frequency to each string in between 100Hz to 880Hz.
    public Guitar37() {
      guitarString37 = new GuitarString[KEYBOARD.length()];
      for(int i = 0; i < guitarString37.length; i++) {
         double frequency = 440 * Math.pow(2, (i - 24) / 12.0);
         guitarString37[i] = new GuitarString(frequency);
      }
   }
   
   // Plays the string using the given pitch parameter.
   // This method ignores the pitch that lies outside the valid range. 
   public void playNote(int pitch) {
      if(pitch <= 12 && pitch >= -24) {
         pitch += 24;
         guitarString37[pitch].pluck();
      }
   }
   
   // Returns true if the passed key is a valid note.
   public boolean hasString(char key) {
      return KEYBOARD.indexOf(key) != -1;
   }
   
   // Throws IllegalArgumentException if the note passed is not valid.
   // Plays the note related to the passed key.
   public void pluck(char key) {
      if(!hasString(key)) {
         throw new IllegalArgumentException();
      }
      int index = KEYBOARD.indexOf(key);
      guitarString37[index].pluck();
   }
   
   // Returns the sum of all the first values of the buffer.
   public double sample() {
      double sum = 0.0;
      for(int i = 0; i < guitarString37.length; i++) {
         sum += guitarString37[i].sample();
      }
      return sum;
   }
   
   // Implements Karplus-Strong algorithm and counts the number of times
   // the method is called.
   public void tic() {
      for(int i = 0; i < guitarString37.length; i++) {
         guitarString37[i].tic();
      }
      tic++;
   }
   
   // Returns the number of times the tic method is called.
   public int time() {
      return tic;
   }
}