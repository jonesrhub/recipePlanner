package com.service.demo.model;

import org.springframework.stereotype.Component;


import javax.inject.Singleton;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component("Amount")
public class Amount {

    private String units;
    private double amount;

    public Amount() {

    }

//    @Autowired
//    public void setAmountExtractor(AmountExtractor amountExtractor) {
//        this.amountExtractor = amountExtractor;
//    }


    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAmountString()
    {
        String str = null;
        DecimalFormat formatter = new DecimalFormat("0.##");
        str = formatter.format(this.getAmount());

        if(str.equals("0")){
            return "";
        } else {
            return str;
        }
    }

    /**
     * Set the amount given a string. Separate out the units from the actual value.
     * @param amount
     */
    public void setAmount(String amount) {
        if(!amount.isEmpty()){
            if(isIngredientAmountFraction(amount)){
                double setterAmount = convertAmountTodouble(amount);
                this.amount = setterAmount;
            } else {
                try {
                    this.amount = AmountExtractor.returnAmountFromIngredient(amount);
                } catch (UpdateException e) {
                    System.out.println("Could not add amount " + String.valueOf(amount));
                }
            }
        }
        this.units = Optional.ofNullable(AmountExtractor.extractUnitFromItem(amount)).get().orElse("-");
    }

    public double convertAmountTodouble(String amount) {
        List<Integer> characterValues = amount.chars().boxed().collect(Collectors.toList());
        double newAmount = 0;
        boolean foundFraction;
        for (Integer i : characterValues) {
            if (i == Measure.HALF.getUnicodeValue()) {
                newAmount = Measure.HALF.getIntegerValue();
                foundFraction = true;
            } else {
                newAmount = Measure.QUARTER.getIntegerValue();
                foundFraction = true;
            }
            if(foundFraction){
                break;
            }
        }
        return (newAmount);
    }

    private boolean isIngredientAmountFraction(String amount){
        List<Integer> characterValues = amount.chars().boxed().collect(Collectors.toList());
        return characterValues.contains(Measure.HALF.getUnicodeValue()) || characterValues.contains(Measure.QUARTER.getUnicodeValue());
    }

    /*
    Private class to parse Amount passed in as a whole string to units and amount
    i.e 100kg
    */
    @Singleton
    @Component
    static class AmountExtractor {

        private AmountExtractor(){

        }

        protected static Optional<String> extractUnitFromItem(String stringAmount){
            String unit = null;
            Pattern p = Pattern.compile("[a-z]+");

            Matcher m = p.matcher(stringAmount);
            while(m.find()) {
                unit = m.group(0);
            }
            return Optional.ofNullable(unit);
        }


        protected static double returnAmountFromIngredient(String amount) throws UpdateException {
            Pattern doublePattern = Pattern.compile("(-?[0-9]+(?:[.][0-9]*)?)");
            Matcher m = doublePattern.matcher(amount);
            double returnAmount = 0;
            while(m.find()) {
                returnAmount = Double.valueOf(m.group(1));
            }
            return returnAmount;

        }
//
//        private static double returndoubleAmountFromIngredient(String origAmount){
//            Pattern p = Pattern.compile("(-?[0-9]+(?:[.][0-9]*)?)");
//            double amount = 0;
//            Matcher m = p.matcher(origAmount);
//            while(m.find()) {
//                amount = Double.valueOf(m.group(1));
//            }
//            return amount;
//        }

//        private static double returnIntegerAmountFromIngredient(String origAmount){
//            Pattern p = Pattern.compile("(\\d+)");
//            double amount = 0;
//            Matcher m = p.matcher(origAmount);
//            while(m.find()) {
//                amount = Integer.valueOf(m.group(1));
//            }
//            return amount;
//        }

    }
}


