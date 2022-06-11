public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character> res = new LinkedListDeque<>();
        for (int pos = 0; pos < word.length(); pos++) {
            char c = word.charAt(pos);
            res.addLast(c);
        }
        return res;
    }

    public String helper(Deque deq) {
        String word = "";
        int l = deq.size();
        for (int i = 0; i < l; i++) {
            word += deq.removeFirst();
        }
        return word;
    }
    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque deq = wordToDeque(word);
        if (deq.removeFirst() == deq.removeLast()) {
            return isPalindrome(helper(deq));
        } else {
            return false;
        }
    }


    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> deq = wordToDeque(word);
        if (cc.equalChars(deq.removeFirst(), deq.removeLast())) {
            return isPalindrome(helper(deq), cc);
        } else {
            return false;
        }
    }
}
