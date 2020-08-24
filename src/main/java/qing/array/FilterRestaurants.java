package qing.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description: FilterRestaurants
 * 给你一个餐馆信息数组 restaurants，其中  restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]。
 * 你必须使用以下三个过滤器来过滤这些餐馆信息。
 * 其中素食者友好过滤器 veganFriendly 的值可以为 true 或者 false，如果为 true 就意味着你应该只包括 veganFriendlyi 为 true 的餐馆，
 * 为 false 则意味着可以包括任何餐馆。此外，我们还有最大价格 maxPrice 和最大距离 maxDistance 两个过滤器，
 * 它们分别考虑餐厅的价格因素和距离因素的最大值。
 * 过滤后返回餐馆的 id，按照 rating 从高到低排序。
 * 如果 rating 相同，那么按 id 从高到低排序。简单起见， veganFriendlyi 和 veganFriendly 为 true 时取值为 1，为 false 时，取值为 0 。
 * 示例 1：
 * 输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 1, maxPrice = 50, maxDistance = 10
 * 输出：[3,1,5]
 * 解释：
 * 这些餐馆为：
 * 餐馆 1 [id=1, rating=4, veganFriendly=1, price=40, distance=10]
 * 餐馆 2 [id=2, rating=8, veganFriendly=0, price=50, distance=5]
 * 餐馆 3 [id=3, rating=8, veganFriendly=1, price=30, distance=4]
 * 餐馆 4 [id=4, rating=10, veganFriendly=0, price=10, distance=3]
 * 餐馆 5 [id=5, rating=1, veganFriendly=1, price=15, distance=1]
 * 在按照 veganFriendly = 1, maxPrice = 50 和 maxDistance = 10 进行过滤后，我们得到了餐馆 3, 餐馆 1 和 餐馆 5（按评分从高到低排序）。
 * 示例 2：
 * 输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 0, maxPrice = 50, maxDistance = 10
 * 输出：[4,3,2,1,5]
 * 解释：餐馆与示例 1 相同，但在 veganFriendly = 0 的过滤条件下，应该考虑所有餐馆。
 * 示例 3：
 * 输入：restaurants = [[1,4,1,40,10],[2,8,0,50,5],[3,8,1,30,4],[4,10,0,10,3],[5,1,1,15,1]], veganFriendly = 0, maxPrice = 30, maxDistance = 3
 * 输出：[4,5]
 * 提示：
 * 1 <= restaurants.length <= 10^4
 * restaurants[i].length == 5
 * 1 <= idi, ratingi, pricei, distancei <= 10^5
 * 1 <= maxPrice, maxDistance <= 10^5
 * veganFriendlyi 和 veganFriendly 的值为 0 或 1 。
 * 所有 idi 各不相同。
 * @author yq
 * @date 2020/8/24.
 */
public class FilterRestaurants {

    public static List<Integer> solution(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {

        return Stream.of(restaurants)
                .filter(x -> (veganFriendly != 1 || x[2] == veganFriendly)
                        && x[3] <= maxPrice
                        && x[4] <= maxDistance)
                .sorted((o1, o2) -> o1[1]==o2[1] ? o2[0]-o1[0] : o2[1]-o1[1])
                .mapToInt(x -> x[0])
                .boxed()
                .collect(Collectors.toList());

    }

    /**
     * 快排序
     * @param restaurants
     * @param veganFriendly
     * @param maxPrice
     * @param maxDistance
     * @return
     */
    public static List<Integer> solutions(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<Integer> ids = new ArrayList<>();
        List<Integer> ratings = new ArrayList<>();
        for(int[] rest : restaurants){
            if(rest[2] >= veganFriendly && rest[3] <= maxPrice && rest[4] <= maxDistance){
                ids.add(rest[0]);
                ratings.add(rest[1]);
            }
        }
        int n = ids.size();
        if(n < 2){
            return ids;
        }
        Integer[] id = ids.toArray(new Integer[n]);
        Integer[] rating = ratings.toArray(new Integer[n]);
        fastSort(id, rating, 0, n - 1);
        List<Integer> res = Arrays.asList(id);
        return res;
    }

    static void fastSort(Integer[] id, Integer[] rating, int start, int end){
        int i = start + 1, j = end, barRating = rating[start], barId = id[start];
        while(i < j){
            if((rating[i] > barRating) || ((rating[i] == barRating) && (id[i] > barId))){
                i++;
            }
            else if((rating[j] < barRating) || ((rating[j] == barRating) && (id[j] < barId))){
                j--;
            }
            else{
                int tempId = id[i], tempRate = rating[i];
                id[i] = id[j];
                rating[i] = rating[j];
                id[j] = tempId;
                rating[j] = tempRate;
                i++;
                j--;
            }
        }
        if(end - start == 1){
            if((rating[end] > barRating) || ((rating[end] == barRating) && (id[end] > id[start]))){
                int tempId = id[start], tempRate = rating[start];
                id[start] = id[j];
                rating[start] = rating[j];
                id[j] = tempId;
                rating[j] = tempRate;
            }
        }
        else if(i > j){
            int tempId = id[start], tempRate = rating[start];
            id[start] = id[j];
            rating[start] = rating[j];
            id[j] = tempId;
            rating[j] = tempRate;
            if(start < j - 1){
                fastSort(id, rating, start, j - 1);
            }
            if(i < end){
                fastSort(id, rating, i, end);
            }
        }
        else {
            if((rating[i] > barRating) || ((rating[i] == barRating) && (id[i] > barId))){
                int tempId = id[start], tempRate = rating[start];
                id[start] = id[j];
                rating[start] = rating[j];
                id[j] = tempId;
                rating[j] = tempRate;
                if(start < j - 1){
                    fastSort(id, rating, start, j - 1);
                }
                if(i + 1 < end){
                    fastSort(id, rating, i + 1, end);
                }
            }
            else{
                int tempId = id[start], tempRate = rating[start];
                id[start] = id[j - 1];
                rating[start] = rating[j - 1];
                id[j - 1] = tempId;
                rating[j - 1] = tempRate;
                if(start < j - 2){
                    fastSort(id, rating, start, j - 2);
                }
                if(i < end){
                    fastSort(id, rating, i, end);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] restaurants = new int[][]{
                new int[]{1,4,1,40,10},
                new int[]{2,8,0,50,5},
                new int[]{3,8,1,30,4},
                new int[]{4,10,0,10,3},
                new int[]{5,1,1,15,1},
        };
        System.out.println(solution(restaurants, 1, 50, 10));
    }
}
