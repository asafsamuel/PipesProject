package cache;

import searcher.Solution;

public interface CacheManager<T>
{
	Solution loadSolution(T level);
	void saveSolution(T level, Solution s);
}
