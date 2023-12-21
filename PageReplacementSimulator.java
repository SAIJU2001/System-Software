import java.util.*;

public class PageReplacementSimulator 
{
    // Simulate FIFO page replacement algorithm
    public static int simulateFIFO(List<Integer> pageRequests, int capacity) 
    {
        Set<Integer> pageSet = new HashSet<>();
        Queue<Integer> fifoQueue = new LinkedList<>();
        int pageFaults = 0;

        for (int page : pageRequests) 
        {
            if (!pageSet.contains(page)) 
            {
                if (pageSet.size() < capacity) 
                {
                    pageSet.add(page);
                } 
                else 
                {
                    int removedPage = fifoQueue.poll();
                    pageSet.remove(removedPage);
                    pageSet.add(page);
                }
                fifoQueue.offer(page);
                pageFaults++;
            }
        }

        return pageFaults;
    }

    // Simulate Optimal page replacement algorithm
    public static int simulateOptimal(List<Integer> pageRequests, int capacity) 
    {
        Set<Integer> pageSet = new HashSet<>();
        Map<Integer, Integer> pageLastUsed = new HashMap<>();
        int pageFaults = 0;

        for (int i = 0; i < pageRequests.size(); i++) 
        {
            int page = pageRequests.get(i);
            if (!pageSet.contains(page)) 
            {
                if (pageSet.size() < capacity) 
                {
                    pageSet.add(page);
                } 
                else 
                {
                    int pageToReplace = findPageToReplaceOptimal(pageRequests, pageSet, i);
                    pageSet.remove(pageToReplace);
                }
                pageSet.add(page);
                pageFaults++;
            }
            pageLastUsed.put(page, i);
        }

        return pageFaults;
    }

    // Helper method to find the page to replace in the Optimal algorithm
    private static int findPageToReplaceOptimal(List<Integer> pageRequests, Set<Integer> pageSet, int currentIndex) 
    {
        int farthestIndex = -1;
        int pageToReplace = -1;

        for (int page : pageSet) 
        {
            int index = pageRequests.subList(currentIndex, pageRequests.size()).indexOf(page);
            if (index == -1) 
            {
                return page; // If a page will not be used again, replace it
            }
            if (index > farthestIndex) 
            {
                farthestIndex = index;
                pageToReplace = page;
            }
        }

        return pageToReplace;
    }

    // Simulate LRU page replacement algorithm
    public static int simulateLRU(List<Integer> pageRequests, int capacity) 
    {
        Set<Integer> pageSet = new HashSet<>();
        Map<Integer, Integer> pageLastUsed = new LinkedHashMap<>();
        int pageFaults = 0;

        for (int page : pageRequests) 
        {
            if (!pageSet.contains(page)) 
            {
                if (pageSet.size() < capacity) 
                {
                    pageSet.add(page);
                } 
                else 
                {
                    int leastRecentlyUsed = pageLastUsed.entrySet().iterator().next().getKey();
                    pageSet.remove(leastRecentlyUsed);
                    pageLastUsed.remove(leastRecentlyUsed);
                }
                pageSet.add(page);
                pageFaults++;
            }
            pageLastUsed.put(page, pageLastUsed.size());
        }

        return pageFaults;
    }

    public static void main(String[] args) 
    {
        List<Integer> pageRequests = Arrays.asList(1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5);
        int capacity = 3;

        System.out.println("FIFO Page Faults: " + simulateFIFO(pageRequests, capacity));
        System.out.println("Optimal Page Faults: " + simulateOptimal(pageRequests, capacity));
        System.out.println("LRU Page Faults: " + simulateLRU(pageRequests, capacity));
    }
}
