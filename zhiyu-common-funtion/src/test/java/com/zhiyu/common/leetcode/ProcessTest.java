package com.zhiyu.common.leetcode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wengzhiyu
 * @since 2022/1/11 16:43
 */
@Slf4j
public class ProcessTest {

    /**
     * https://leetcode-cn.com/problems/two-sum/
     */
    @Test
    public void question1() {
        int[] nums = new int[]{1, 2, 3, 4};
        String numStr = Arrays.toString(twoSum(nums, 7));
        //String numStr = Arrays.toString(twoSum1(nums, 7));
        log.info(numStr);
    }

    /**
     * https://leetcode-cn.com/problems/add-two-numbers/
     * 给你两个非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * <p>
     * 示例 2：
     * <p>
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     * <p>
     * 示例 3：
     * <p>
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     */
    @Test
    public void question2() {
        int[] l1Arr = new int[]{2, 4, 3};
        int[] l2Arr = new int[]{5, 6, 4};
        ListNode l1 = wrapListNode(l1Arr);
        ListNode l2 = wrapListNode(l2Arr);

    }

    private ListNode wrapListNode(int[] array) {
        if (array.length == 0) {
            return new ListNode();
        }
        ListNode listNode = new ListNode();
        for (int value : array) {
            listNode = new ListNode(value, listNode);
        }
        return listNode;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int i = l1.getVal() + l2.getVal();

        return null;
    }


    @Data
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
