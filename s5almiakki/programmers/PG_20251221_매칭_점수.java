import java.util.*;
import java.util.regex.*;

public class PG_20251221_매칭_점수 {

    class Solution {

        private static Pattern headPattern = Pattern.compile("\\<head\\>.*?"
                + "\\<meta\\s*property\\s*\\=\\s*\\Q\"og:url\"\\E\\s*content\\s*\\=\\s*\"(?<url>\\Qhttps://\\E.*?)\"\\s*\\/\\>"
                + ".*?\\Q</head>\\E", Pattern.DOTALL);
        private static Pattern aPattern = Pattern.compile("\\<a\\s*href\\s*=\\s*\"(?<externalLink>\\Qhttps://\\E.*?)\"\\>", Pattern.DOTALL);
        private static Pattern tagPattern = Pattern.compile("\\<.*?\\>", Pattern.DOTALL);
        private static Pattern wordPattern = Pattern.compile("[a-zA-Z]+");

        public int solution(String word, String[] pages) {
            word = word.toLowerCase();
            Map<String, Integer> urlToIdxMap = new HashMap<>();
            List<Page> pageList = new ArrayList<>();
            for (String page : pages) {
                Matcher headMatcher = headPattern.matcher(page);
                headMatcher.find();
                String url = headMatcher.group("url");

                Matcher aMatcher = aPattern.matcher(page);
                Collection<String> externalLinks = new HashSet<>();
                while (aMatcher.find()) {
                    externalLinks.add(aMatcher.group("externalLink"));
                }

                String text = tagPattern.matcher(page).replaceAll("");
                Matcher wordMatcher = wordPattern.matcher(text);
                int baseScore = 0;
                while (wordMatcher.find()) {
                    String w = wordMatcher.group().toLowerCase();
                    if (word.equals(w)) {
                        baseScore++;
                    }
                }

                urlToIdxMap.put(url, pageList.size());
                pageList.add(new Page(externalLinks, baseScore));
            }

            for (Page p : pageList) {
                Collection<String> externalLinks = p.externalLinks;
                for (String externalLink : externalLinks) {
                    Integer idx = urlToIdxMap.get(externalLink);
                    if (idx == null) {
                        continue;
                    }
                    Page externalPage = pageList.get(idx);
                    externalPage.linkScore += p.baseScore / (double) externalLinks.size();
                }
            }

            int answer = 0;
            double maxMatchingScore = pageList.get(0).getMatchingScore();
            for (int i = 1; i < pageList.size(); i++) {
                Page p = pageList.get(i);
                double matchingScore = p.getMatchingScore();
                if (maxMatchingScore < matchingScore) {
                    maxMatchingScore = matchingScore;
                    answer = i;
                }
            }
            return answer;
        }

        private static class Page {

            public Collection<String> externalLinks;
            public int baseScore;
            public double linkScore = .0;

            public Page(Collection<String> externalLinks, int baseScore) {
                this.externalLinks = externalLinks;
                this.baseScore = baseScore;
            }

            public double getMatchingScore() {
                return (double) baseScore + linkScore;
            }

        }

    }

}
