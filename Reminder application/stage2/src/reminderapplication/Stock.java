package reminderapplication;

public class Stock {
    //    class CaptionMatcher implements ComponentMatcher {
//        private String caption;
//
//        public CaptionMatcher(String caption) {
//            this.setCaption(caption);
//        }
//
//        @Override
//        public boolean matches(Component comp) {
//            if (comp != null && comp instanceof JButton) {
//                if (caption.equals(((JButton) comp).getText())) {
//                    return true;
//                }
//            }
//            return false;
//        }
//
//
//        public void setCaption(String caption) {
//            this.caption = caption;
//        }
//
//    }


//        try {
//
//            set_reminder = WindowFinder.findFrame("Set Reminder")
//                    .withTimeout(200)
//                    .using(getWindow().robot());
//            System.out.println(set_reminder.toString());
//        } catch (WaitTimedOutError e) {
//            System.out.println("Catch");
//            return wrong("Timeout waiting for the window ");
//        }


//        set_reminder.requireTitle("Set Reminder");

//            click1(new CaptionMatcher("Set Reminder"));


//        finder = getWindow().robot().finder();
//        click1(new CaptionMatcher("Cancel"));
//        set_reminder.requireVisible();
//        addButton.click();
//        set_reminder.requireVisible();
//
//        click1(new CaptionMatcher("Cancel"));
//        set_reminder.requireNotVisible();
//
//        click1(new CaptionMatcher("OK"));

//    private void click1(CaptionMatcher matcher) throws Exception {
//        Component tmp = null;
//        long start = System.currentTimeMillis();
//
//        while (tmp == null) {
//            try {
//                tmp = finder.find(matcher);
//                final Component btn = tmp;
//                Executors.newFixedThreadPool(2).execute(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        ((JButton) btn).doClick();
//                    }
//                });
//
//            } catch (Exception e) {
//                //e.printStackTrace();
//            }
//            Thread.yield();
//            if (System.currentTimeMillis() - start > 1000) {
//                throw new Exception("text to be thrown");
//            }
//
//        }
//
//        Thread.sleep(2000);
//    }
}
