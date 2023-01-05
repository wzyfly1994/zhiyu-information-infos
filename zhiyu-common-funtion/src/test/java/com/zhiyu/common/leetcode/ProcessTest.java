package com.zhiyu.common.leetcode;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
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
     * 两数之和
     * https://leetcode-cn.com/problems/two-sum/
     * <p>
     * 给定一个整数数组 nums和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那两个整数，并返回它们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
     * 你可以按任意顺序返回答案。
     */
    @Test
    public void question1() {
        int[] nums = new int[]{1, 2, 3, 4};
        String numStr = Arrays.toString(twoSum(nums, 3));
        // String numStr = Arrays.toString(twoSumOfficial(nums, 7));
        log.info(numStr);
    }

    /**
     * 两数相加
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
     * <p>
     * [9]
     * [1,9,9,9,9,9,9,9,9,9]
     */
    @Test
    public void question2() {
        int[] l1Arr = new int[]{9};
        int[] l2Arr = new int[]{1, 9, 9, 9, 9, 9, 9, 9, 9, 9};
        ListNode l1 = wrapListNodes(l1Arr);
        ListNode l2 = wrapListNodes(l2Arr);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(addTwoNumbers(l1, l2));
        // 官方解
        //System.out.println(addTwoNumbersOfficial(l1, l2));
        ArrayList<Object> list = new ArrayList<>(16);

        list.add(1);


    }


    /**
     * 两数之和
     * <p>
     * me
     * *************************************************************************************************
     */

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

    /**
     * 官方解
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumOfficial(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }


    /**
     * 两数相加
     * me
     * *************************************************************************************************
     */

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigDecimal var = getNodeVal(l1, "").add(getNodeVal(l2, ""));
        String strVar = var.toString();
        int[] arr = new int[strVar.length()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(String.valueOf(strVar.charAt(i)));
        }
        System.out.println("arr++" + Arrays.toString(arr));
        return wrapListNode(arr);
    }

    /**
     * 倒序
     *
     * @param array
     * @return
     */
    private ListNode wrapListNode(int[] array) {
        if (array.length == 0) {
            return new ListNode();
        }
        ListNode listNode = null;
        for (int value : array) {
            listNode = new ListNode(value, listNode);
        }
        return listNode;
    }

    /**
     * 正序
     *
     * @param array
     * @return
     */
    private ListNode wrapListNodes(int[] array) {
        if (array.length == 0) {
            return new ListNode();
        }
        ListNode listNode = null;
        for (int i = array.length - 1; i < array.length && i >= 0; i--) {
            listNode = new ListNode(array[i], listNode);
        }
        return listNode;
    }


    private BigDecimal getNodeVal(ListNode node, String strVal) {
        if (node.next == null) {
            return new BigDecimal(node.val + strVal);
        }
        strVal = node.val + strVal;
        return getNodeVal(node.next, strVal);
    }


    /**
     *
     * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
     *
     * 我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。具体而言，如果当前两个链表处相应位置的数字为 n1,n2n1,n2，进位值为 \textit{carry}carry，则它们的和为 n1+n2+\textit{carry}n1+n2+carry；其中，答案链表处相应位置的数字为 (n1+n2+\textit{carry}) \bmod 10(n1+n2+carry)mod10，而新的进位值为 \lfloor\frac{n1+n2+\textit{carry}}{10}\rfloor⌊
     * 10
     * n1+n2+carry
     * ​
     *  ⌋。
     *
     * 如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个 00 。
     *
     * 此外，如果链表遍历结束后，有 \textit{carry} > 0carry>0，还需要在答案链表的后面附加一个节点，节点的值为 \textit{carry}carry。
     *

     * 官方解
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbersOfficial(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
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

    /**
     * *************************************************************************************************
     */

}
