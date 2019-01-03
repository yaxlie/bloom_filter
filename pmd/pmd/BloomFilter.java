package pmd;

import java.math.BigInteger;
import java.util.BitSet;
import java.util.Random;

public class BloomFilter {
    int size = 0;
    int k = 0;
    int p;
    int a[];
    int b[];

    Random rand = new Random();
    BitSet bitSet;


    public BloomFilter(int size, int k) {
        this.size = size;
        this.k = k;

        a = new int[k];
        b = new int[k];

        p = size + 1; // prime number

        bitSet = new BitSet(size);

        int p = size + 1; // prime number
        while(!isPrime(p)){
            p++;
        }

        for (int i=0; i<k;i++){
            a[i] = rand.nextInt(p+1);
            b[i] = rand.nextInt(p+1);
        }
    }

    public int hash(int hashNo, int valueToHash) throws Exception {
//        int a = rand.nextInt(p+1);
//        int b = rand.nextInt(p);

        int g = (a[hashNo] * valueToHash + b[hashNo])%p;
        int h  = g%size;

        return h<0? -h: h;
    }

    public void add(int key) throws Exception{
        for(int i=0; i<k; i++)
        {
            bitSet.set(hash(i, key));
        }
    }

    public Boolean contains(int key) throws Exception{

        for(int i=0; i<k; i++)
        {
            int hashedValue = hash(i, key);
            if(!bitSet.get(hashedValue))
            {
                return false;
            }
        }

        return true;
    }

    public boolean isPrime(int number) {
        BigInteger bigInt = BigInteger.valueOf(number);
        return bigInt.isProbablePrime(100);
    }
}
