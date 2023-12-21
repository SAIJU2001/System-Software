import java.util.*;

class MemoryBlock 
{
    int id;
    int size;
    boolean allocated;

    public MemoryBlock(int id, int size) 
    {
        this.id = id;
        this.size = size;
        this.allocated = false;
    }
}

class Process 
{
    int id;
    int size;
    boolean allocated;

    public Process(int id, int size) 
    {
        this.id = id;
        this.size = size;
        this.allocated = false;
    }
}

public class MemoryAllocationSimulator 
{
    // Simulate First-fit memory allocation algorithm
    public static void firstFit(List<MemoryBlock> memoryBlocks, List<Process> processes) 
    {
        for (Process process : processes) 
        {
            for (MemoryBlock block : memoryBlocks) 
            {
                if (!block.allocated && block.size >= process.size) 
                {
                    block.allocated = true;
                    process.allocated = true;
                    System.out.println("Process " + process.id + " allocated to Memory Block " + block.id);
                    break;
                }
            }
            if (!process.allocated) 
            {
                System.out.println("Process " + process.id + " cannot be allocated");
            }
        }
    }

    // Simulate Best-fit memory allocation algorithm
    public static void bestFit(List<MemoryBlock> memoryBlocks, List<Process> processes) 
    {
        for (Process process : processes) {
            MemoryBlock bestFitBlock = null;

            for (MemoryBlock block : memoryBlocks) 
            {
                if (!block.allocated && block.size >= process.size) 
                {
                    if (bestFitBlock == null || block.size < bestFitBlock.size) 
                    {
                        bestFitBlock = block;
                    }
                }
            }

            if (bestFitBlock != null) 
            {
                bestFitBlock.allocated = true;
                process.allocated = true;
                System.out.println("Process " + process.id + " allocated to Memory Block " + bestFitBlock.id);
            } 
            else 
            {
                System.out.println("Process " + process.id + " cannot be allocated");
            }
        }
    }

    // Simulate Worst-fit memory allocation algorithm
    public static void worstFit(List<MemoryBlock> memoryBlocks, List<Process> processes) 
    {
        for (Process process : processes) 
        {
            MemoryBlock worstFitBlock = null;

            for (MemoryBlock block : memoryBlocks) 
            {
                if (!block.allocated && block.size >= process.size) 
                {
                    if (worstFitBlock == null || block.size > worstFitBlock.size) 
                    {
                        worstFitBlock = block;
                    }
                }
            }

            if (worstFitBlock != null) 
            {
                worstFitBlock.allocated = true;
                process.allocated = true;
                System.out.println("Process " + process.id + " allocated to Memory Block " + worstFitBlock.id);
            } 
            else 
            {
                System.out.println("Process " + process.id + " cannot be allocated");
            }
        }
    }

    public static void main(String[] args) 
    {
        List<MemoryBlock> memoryBlocks = Arrays.asList(
            new MemoryBlock(1, 100),
            new MemoryBlock(2, 200),
            new MemoryBlock(3, 50),
            new MemoryBlock(4, 300),
            new MemoryBlock(5, 150)
        );

        List<Process> processes = Arrays.asList(
            new Process(101, 80),
            new Process(102, 200),
            new Process(103, 120),
            new Process(104, 250),
            new Process(105, 100)
        );

        System.out.println("First-Fit:");
        firstFit(new ArrayList<>(memoryBlocks), new ArrayList<>(processes));

        System.out.println("\nBest-Fit:");
        bestFit(new ArrayList<>(memoryBlocks), new ArrayList<>(processes));

        System.out.println("\nWorst-Fit:");
        worstFit(new ArrayList<>(memoryBlocks), new ArrayList<>(processes));
    }
}
