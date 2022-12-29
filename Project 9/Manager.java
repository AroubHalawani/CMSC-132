package manager;

import java.util.ArrayList;

public class Manager {
	/**
	 * An array of Blocks that is used to represent the memory to be managed
	 */
	private ArrayList<Block> memory;
	/**
	 * The value of this field indicates how to search for a fit for allocated memory.
	 * A value of 0 means you should use the first fit policy, while a value of 1 means
	 * you should use the best fit policy. The default value should be 0.
	 */
	private int fitPolicy;
	
	/**
	 * This is a pointer to the head of the available blocks list
	 */
	private Block headAvailable;
	
	/**
	 * A public constructor for the Manager class
	 * @param initialBlockSizes The sizes of each block to be managed. The unit of memory is 4 bytes, so a size of
	 * 1 means 4 bytes, 2 means 8 bytes and so on. This does not include the blocks overhead.
	 * The number of sizes in blockSizes matches the number of blocks.
	 */
	public Manager(int[] initialBlockSizes) {
		//Initializing the memory
		fitPolicy = 0;
		headAvailable = null;
		memory = new ArrayList<Block>();
		for(int count=0; count<initialBlockSizes.length; count++) {			
			Block aBlock = new Block();
			aBlock.inUse = 0;
			aBlock.prevInUse = 0;
			aBlock.size = initialBlockSizes[count];
			aBlock.memory = new String[initialBlockSizes.length];
			memory.add(aBlock);
			if (count == 0) {
				aBlock.prevBlock = null;
			}
			else {
				aBlock.prevBlock = memory.get(count-1);
				memory.get(count-1).nextBlock = aBlock;
			}			
		}
		if (memory.size() == 0) {
			headAvailable = null;
		} else {
			headAvailable = memory.get(0);
		}
	}
	
	/**
	 * Allocate a block of memory based on the passed in size and the the fit policy.
	 * When using the best fit policy, if multiple blocks could be the best fit, use the first one 
	 * in the order the blocks are chained together in the available block list.
	 * @param size The number of units of memory to allocate
	 * @return A Block info object for the allocated memory.
	 */
	public Block malloc(int size) {
		Block blockOfMemory  = null;
		Block block = null;
		// The first fit policy
				if (fitPolicy == 0) { 
					for (int count=0; count < memory.size(); count++) {				
						if (memory.get(count).inUse == 0 && memory.get(count).size>=size) {
							//Removing the node
							if (memory.get(count).size - size == 0) { 
								
								if (memory.get(count).prevBlock != null) {
									memory.get(count).inUse = 1;
									memory.get(count).prevBlock.nextBlock = memory.get(count).nextBlock;
								}
								if (memory.get(count).nextBlock != null) {
									memory.get(count).nextBlock.prevBlock = memory.get(count).prevBlock;
							
								}
								
							} else {  

								// Reducing the node to forward and reverse the link
								Block tempBlock = new Block();
								tempBlock.size = memory.get(count).size - size;
								memory.get(count).prevBlock.nextBlock = tempBlock;
								tempBlock.nextBlock = memory.get(count).nextBlock;
								memory.get(count).nextBlock.prevBlock = tempBlock;
								tempBlock.prevBlock = memory.get(count).prevBlock;		
								tempBlock.prevInUse = 1;
								memory.add(count+1, tempBlock);
								
							}
							//Unhooks 
							blockOfMemory  = memory.get(count);
							blockOfMemory .inUse = 1;
							blockOfMemory .size = size;
							blockOfMemory .nextBlock = null;
							blockOfMemory .prevBlock = null;
							break;
						}
					}
				}
		// The best fit policy
		if (fitPolicy == 1) { 
			for (int count=0; count < memory.size(); count++) { 
				// Finding the first fit that is available
				if (memory.get(count).inUse == 0 && memory.get(count).size >= size) {
					block = memory.get(count);
					break;
				}
			}
			int theBlockDiffrence  = block.size - size;
			// Going over the memory to find the best fit
			for (int count=0; count < memory.size(); count++) {  
				if (memory.get(count).inUse == 0 && memory.get(count).size>=size) {
					if (memory.get(count).size - size < theBlockDiffrence ) {
						theBlockDiffrence  = memory.get(count).size - size;
						block = memory.get(count);
					}
				}
			}
			block.inUse = 1;
			blockOfMemory = block;
		}
		
		// Returning the block information
		return blockOfMemory ;
	}	
	/**
	 * Make a previously allocated block of memory become available again. If either or both of the blocks adjacent to it are
	 * also available, join them together to form one large block.
	 * If the block passed in was not previously allocated, i.e it is not one of the blocks currently in memory indicated
	 * as being in use, return false. If the block passed in is free, return false
	 * @param block The block of memory to be deallocated
	 * @retrun true if free was successful, false otherwise
	 */
	public boolean free(Block block) {
		// free was not successful
		if (!memory.contains(block) || block.inUse == 0 ) {
			return false;
		}	
		// If the block passed in was not previously allocated
		int index = memory.indexOf(block);
		if (index == 0) {  
			if (memory.size() > 1) {
				Block next = memory.get(index + 1);
				if (next.inUse ==  0) { 
					block.size += next.size;
					block.nextBlock = next.nextBlock;
					block.prevBlock = null;
				}
			} else {
				block.inUse = 0;
				block.nextBlock = null;
				block.prevBlock = null;
			}
		} else {
			Block previousBlock = memory.get(index - 1);
			Block nextBlock = memory.get(index + 1);
			if (previousBlock.inUse == 0 && nextBlock.inUse != 0) { 
				previousBlock.nextBlock = nextBlock;
				nextBlock.prevBlock = previousBlock;
				previousBlock.size += block.size;
				memory.remove(block);
			}
			if (previousBlock.inUse == 0 && nextBlock.inUse == 0) { 
				previousBlock.nextBlock = nextBlock.nextBlock;
				nextBlock.nextBlock.prevBlock = previousBlock;
				previousBlock.size += block.size + nextBlock.size;
				memory.remove(block);
				memory.remove(nextBlock);
			}
		}
		return true;
	}
	public ArrayList<Block> getMemory() {
		return this.memory;
	}
	public Block getAvailableBlockList() {
		return this.headAvailable;
	}
	public void setFitPolicy(int fitPolicy) {
		this.fitPolicy = fitPolicy;
	}	
	public int getFitPolicy() {
		return this.fitPolicy;
	}
}