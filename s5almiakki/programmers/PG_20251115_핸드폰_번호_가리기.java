
public class PG_20251115_핸드폰_번호_가리기 {

    class Solution {

        public String solution(String phone_number) {
            StringBuilder sb = new StringBuilder();
            int length = phone_number.length();
            for (int i = 4; i < length; i++) {
                sb.append('*');
            }
            for (int i = length - 4; i < length; i++) {
                sb.append(phone_number.charAt(i));
            }
            return sb.toString();
        }

    }

}
