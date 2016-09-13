package com.ligstd.homework.calculators;

import com.ligstd.homework.models.Command;
import com.ligstd.homework.models.SubItem;

import java.util.*;

/**
 * Created by tt030 on 2016/9/12.
 */
public class SimplifyCalculator extends CalculatorBase {

    @Override
    public void Calculate(){
        Simplify();
        Merge();
    }

    private void Simplify() {
        getNewExpression().clear();
        Map<String, Double> expressions = getCommand().getExpressions();
        for (SubItem subItem : getExpression()) {
            Map<String, Double> variables = subItem.getVariables();
            Double newCoefficient = subItem.getCoefficient();
            Map<String, Double> newVariables = new HashMap<>();
            for (String variableName : variables.keySet()) {
                Double power = variables.get(variableName);
                if (expressions.containsKey(variableName)) {
                    Double value = expressions.get(variableName);
                    newCoefficient *= Math.pow(value, power);
                } else {
                    newVariables.put(variableName, power);
                }
            }
            getNewExpression().add(new SubItem(newCoefficient, newVariables));
        }
    }
}