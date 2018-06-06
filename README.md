# codeProj
算法汇总

1，快速排序
快速排序（Quicksort）是对冒泡排序的一种改进。由C. A. R. Hoare在1962年提出。

通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
实现方法：
快速排序一般基于递归实现。其思路是这样的：

1.选定一个合适的值（理想情况中值最好，但实现中一般使用数组第一个值）,称为“枢轴”(pivot)。

2.基于这个值，将数组分为两部分，较小的分在左边，较大的分在右边。

3.可以肯定，如此一轮下来，这个枢轴的位置一定在最终位置上。

4.对两个子数组分别重复上述过程，直到每个数组只有一个元素。

5.排序完成。

示意图：
![](https://github.com/zhxhcoder/codeProj/blob/master/screenshots/Sorting_quicksort_anim.gif)

很多实际的项目中使用了快排算法。但通常对算法都进行了调整（tuning），比如Java.util.Arrays类中的sort函数就使用了快排算法，但使用了双参考值（Dual-Pivot Quicksort）等一些改进措施。由于快排算法为递归算法，可以用循环代替递归函数调用，改进性能。


2，动态规划

动态规划所处理的问题是一个多阶段决策问题，一般由初始状态开始，通过对中间阶段决策的选择，达到结束状态。这些决策形成了一个决策序列，同时确定了完成整个过程的一条活动路线(通常是求最优的活动路线)。如图所示。动态规划的设计都有着一定的模式，一般要经历以下几个步骤。
初始状态→│决策１│→│决策２│→…→│决策ｎ│→结束状态
图1 动态规划决策过程示意图
(1)划分阶段：按照问题的时间或空间特征，把问题分为若干个阶段。在划分阶段时，注意划分后的阶段一定要是有序的或者是可排序的，否则问题就无法求解。
(2)确定状态和状态变量：将问题发展到各个阶段时所处于的各种客观情况用不同的状态表示出来。当然，状态的选择要满足无后效性。
(3)确定决策并写出状态转移方程：因为决策和状态转移有着天然的联系，状态转移就是根据上一阶段的状态和决策来导出本阶段的状态。所以如果确定了决策，状态转移方程也就可写出。但事实上常常是反过来做，根据相邻两个阶段的状态之间的关系来确定决策方法和状态转移方程。
(4)寻找边界条件：给出的状态转移方程是一个递推式，需要一个递推的终止条件或边界条件。
一般，只要解决问题的阶段、状态和状态转移决策确定了，就可以写出状态转移方程（包括边界条件）。
实际应用中可以按以下几个简化的步骤进行设计：
（1）分析最优解的性质，并刻画其结构特征。
（2）递归的定义最优解。
（3）以自底向上或自顶向下的记忆化方式（备忘录法）计算出最优值
（4）根据计算最优值时得到的信息，构造问题的最优解

[文章链接](https://www.jianshu.com/p/22d716001dd5)

3，



