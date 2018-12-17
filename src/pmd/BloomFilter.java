package pmd;

import java.util.BitSet;

public class BloomFilter {
    int size = 0;
    int k = 0;
    BitSet bitSet;

    public BloomFilter(int size, int k) {
        this.size = size;
        this.k = k;
        bitSet = new BitSet(size);
    }

    public int hash(int hashNo, int valueToHash) throws Exception {
        if(hashNo <=0){
            throw new Exception("hashNo <= 0");
        }
        return (valueToHash * hashNo + 2*(hashNo-1)) % size;
    }

    public void add(int key) throws Exception{
        for(int i=1; i<=k; i++)
        {
            bitSet.set(hash(i, key));
        }
    }

    public Boolean contains(int key) throws Exception{

        for(int i=1; i<=k; i++)
        {
            int hashedValue = hash(i, key);
            if(!bitSet.get(hashedValue))
            {
                return false;
            }
        }

        return true;
    }
}
