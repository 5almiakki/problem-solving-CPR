import java.util.*;

public class PG_20251105_1차_추석_트래픽 {

    class Solution {

        private static final int SECOND_TO_MS_FACTOR = 1000;
        private static final int MINUTE_TO_MS_FACTOR = SECOND_TO_MS_FACTOR * 60;
        private static final int HOUR_TO_MS_FACTOR = MINUTE_TO_MS_FACTOR * 60;

        public int solution(String[] lines) {
            SortedMap<Integer, Integer> timeToCountMap = new TreeMap<>();
            for (String line : lines) {
                String[] splitted = line.split(" ");
                int endTime = toIntTime(splitted[1]) + 1000;
                String[] splittedDuration = splitted[2].replace("s", "").split("\\.");
                int duration = Integer.parseInt(splittedDuration[0]) * SECOND_TO_MS_FACTOR;
                if (splittedDuration.length == 2) {
                    duration += Integer.parseInt(splittedDuration[1].replace("s", ""));
                }
                int startTime = endTime - duration - 999;
                timeToCountMap.put(startTime, timeToCountMap.getOrDefault(startTime, 0) + 1);
                timeToCountMap.put(endTime, timeToCountMap.getOrDefault(endTime, 0) - 1);
                // System.out.println("start: " + startTime + ", end: " + endTime);
            }
            // System.out.println(timeToCountMap);
            int[] accumulations = new int[timeToCountMap.size()];
            int timeIdx = 0;
            Iterator<Map.Entry<Integer, Integer>> it = timeToCountMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<Integer, Integer> entry = it.next();
                accumulations[timeIdx] += entry.getValue();
                timeIdx++;
            }
            int answer = accumulations[0];
            for (int i = 1; i < accumulations.length; i++) {
                accumulations[i] += accumulations[i - 1];
                answer = Math.max(answer, accumulations[i]);
            }
            // System.out.println(Arrays.toString(accumulations));
            return answer;
        }

        private int toIntTime(String h, String m, String s, String ms) {
            return Integer.parseInt(h) * HOUR_TO_MS_FACTOR
                    + Integer.parseInt(m) * MINUTE_TO_MS_FACTOR
                    + Integer.parseInt(s) * SECOND_TO_MS_FACTOR
                    + Integer.parseInt(ms);
        }

        private int toIntTime(String hmsms) {
            String[] hms = hmsms.split(":");
            String[] sms = hms[2].split("\\.");
            return toIntTime(hms[0], hms[1], sms[0], sms[1]);
        }

    }

}
