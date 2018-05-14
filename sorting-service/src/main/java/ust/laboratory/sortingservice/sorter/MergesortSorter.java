package ust.laboratory.sortingservice.sorter;

import java.util.Arrays;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class MergesortSorter extends Sorter {

	private static final Logger Log = LoggerFactory.getLogger(MergesortSorter.class);

	public MergesortSorter() {
		super("MERGESORT-"+UUID.randomUUID(), Sorter.MERGESORT);
	}

	@Override
	public int[] sort(int[] input) {		
		Log.debug("sort: {}", input);
		Log.debug("Sort: name {}", getName());
		return mergeSortOrder(input.clone());
	}

	private int[] mergeSortOrder(int vec[]){
        if(vec.length<=1) return null;
        int half= vec.length/2;
        int left[]=Arrays.copyOfRange(vec, 0, half);
        int right[]=Arrays.copyOfRange(vec, half, vec.length);
        mergeSortOrder(left);
        mergeSortOrder(right);       
        vectorCombination(vec, left, right);
        return vec;
	}

	private void vectorCombination(int v[], int left[],int right[]){
        int i=0;
        int j=0;
        for(int k=0;k<v.length;k++){
            if(i>=left.length){
                v[k]=right[j];
                j++;
                continue;
            }
            if(j>=right.length){
                v[k]=left[i];
                i++;
                continue;
            }
            if(left[i]<right[j]){
                v[k]=left[i];
                i++;
            }else{
                v[k]=right[j];
                j++;
            }
        }
	}

}
