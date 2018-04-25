package solver;

import searcher.Solution;

public interface Solver<T>
{
	Solution solve(T level);
}
