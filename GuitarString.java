// This program models a vibrating guitar string of a given frequency.

import java.util.*;
public class GuitarString {
   // Stores sound data
   private Queue<Double> ringBuffer;
   // Class constant 
   public static final double ENERGY_DECAY_FACTOR = 0.996;
   
   // Contructs a GuitarString object using frequency. The string is at rest. 
   // Throws an IllegalArgumentException if the frequency is less than or equal to 0
   // or if the size of the ring buffer is less than 2. 
   // N represents the size of the ring buffer. 
   public GuitarString(double frequency) {
      int N = (int)Math.round(StdAudio.SAMPLE_RATE / frequency);
      if(frequency <= 0 || N < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for(int i = 0; i < N; i++) {
         ringBuffer.add(0.0);
      }
   }
   
   // Contructs a GuitarString object using buffer values initialized from the 
   // array. This is used for testing purposes only.
   // Throws an IllegalArgumentException if the passed array has less than 2 elements.
   public GuitarString(double[] init) {
      if(init.length < 2) {
         throw new IllegalArgumentException();
      }
      ringBuffer = new LinkedList<Double>();
      for(int i = 0; i < init.length; i++) {
         ringBuffer.add(init[i]);
      }
   }
   
   // Replaces the N elements in the ring buffer with N random values between 
   // -0.5(inclusive) and 0.5(exclusive).
   public void pluck() {
      for(int i = 0; i < ringBuffer.size(); i++) {
         Random r = new Random();
         double rand = -0.5 + (0.5 - (-0.5)) * r.nextDouble();
         ringBuffer.remove();
         ringBuffer.add(rand);
      }    
   }
   
   // Applies the Karplus-Strong update once to the ring buffer.
   public void tic() {
      double one = ringBuffer.remove();
      double two = ringBuffer.remove();
      double average = ((one + two) / 2) * ENERGY_DECAY_FACTOR;
      ringBuffer.add(average);
   }
   
   // Returns first value of the ring buffer.
   public double sample() {
      return ringBuffer.peek();
   }
} 