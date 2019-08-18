package leetcode.find_middien;

/**
 * @author ruby_
 * @create 2018-12-07-8:57 PM
 */

class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        //m is shorter list (B list)
        int n = A.length;
        int m = B.length;

        if(n < m) {
            int[] C = A; A=B; B=C;
            int tmp = n; n=m; m=tmp;
        }

        if(m==0)
            return median(A);

        //Make sure the total length of A&B list >=4
        if(m == 1){
            if(n==1) return (A[0] + B[0]) /2.0;
            else if(n==2) {
                if(A[0] >= B[0])
                    return A[0];
                else if(A[1] <= B[0])
                    return A[1];
                else
                    return B[0];
            } else
                return median(A,B[0]);
        }

        //length of per side, must be determined by m+n
        int len = (m+n)/2;
        if((m+n)%2 != 0)
            len+=1;

        //Find the element in B for the separation
        int pB = locateIndex((m-1)/2, 0, m-1, len, A, B);
        int pA = len - pB -2;
        if(pB == -1) {//Scenario 1: All B elements are in the right part
            if(n > m) {
                pA = n + m - len;
                if ((n + m) % 2 == 0)
                    return (Math.min(A[pA],B[0]) + A[pA - 1]) / 2.0;
                else
                    return A[pA];
            } else//A, B same size
                return (A[n-1] + B[0])/2.0;
        } else if(pB == m -1) {
            if(n>m) {
//                pA = len - m - 1;
                if ((n + m) % 2 == 0)
                    return (Math.max(A[pA], B[pB]) + A[pA + 1]) / 2.0;
                else
                    return Math.max(A[pA], B[pB]);
            } else
                return (A[0] + B[m-1])/2.0;
        } else {
                if((n+m)%2 == 0)
                    return (Math.max(A[pA], B[pB]) + Math.min(A[pA+1], B[pB+1]))/2.0;
                else
                    return Math.max(A[pA], B[pB]);
        }
    }

    /**
     * Locate the index at B so that all B[0]...B[j] belongs to the left part of the whole list
     * @param A: A must be largeer than B
     * Has 3 scenario:
     * 1) J belongs (0, m-1), free move general case
     * 2) j == 0, but Bj > Ai+1, means all B is greater and should be put in right part, return m in this case
     * 3) j == m-1, but Ai>Bj+1, means all B is smaller and should be put in left part, return -1 in this case
     * 4) Min-Max boarder to set up the search range!
     */
    private int locateIndex(int j, int min, int max, int len, int[] A, int[] B) {
        int i = len -2 - j;
//*** Recursive solution only works for small data sets, cause stackOverflow if input large
        if(j < max && j >= min) {
            if(A[i] > B[j+1]) {
                if(j!=max-1)
                    return locateIndex((j+max)/2, j, max, len, A, B); //Scenario 1
                else
                    return max;
            }

            if(B[j] > A[i+1]) {
                if(j != 0)
                    return locateIndex((min+j)/2, min, j, len, A, B);
                else
                    return -1; //Scenario 3
            }

        }

        return j;
    }

    private double median(int[] C) {
        int n = C.length;

        if(n%2 == 0)
            return (C[(n-1)/2]+C[n/2])/2.0;
        else
            return C[(n-1)/2];
    }

    //if B has only 1 value, binary search to insert it to A then get median
    //@param C : C is required to have at least 3 items
    private double median(int[]C, int b) {
        int n = C.length;
        double mid =  median(C);

        if(n%2 == 0) {
            if(b <= mid)
                return Math.max(b, C[(n-1)/2]);
            else
                return Math.min(b, C[n/2]);
        } else {
            if(b <= mid)
                return (Math.max(b, C[(n-1)/2-1]) + mid)/2.0;
            else
                return (Math.min(b, C[(n-1)/2+1]) + mid)/2.0;
        }
    }

}
