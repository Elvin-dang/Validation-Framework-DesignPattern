package Engine;

import AnnotationCustom.NegativeOrZero;

import java.util.HashMap;
import java.util.Hashtable;

import static Engine.CheckerType.*;

public class ConstraintCheckerPool {
    private static final ConstraintCheckerPool constraintsCheckerPool= new ConstraintCheckerPool();

    private final HashMap<CheckerType, ConstraintChecker> checkerPool;

    private ConstraintCheckerPool() {
        this.checkerPool = new HashMap<>();
        this.checkerPool.put(EMAIL_CHECKER, new EmailChecker());
        this.checkerPool.put(MIN_CHECKER, new MinChecker());
        this.checkerPool.put(MAX_CHECKER, new MaxChecker());
        this.checkerPool.put(LENGTH_CHECKER, new LengthChecker());
        this.checkerPool.put(NEGATIVE_CHECKER, new NegativeChecker());
        this.checkerPool.put(POSITIVE_CHECKER, new PositiveChecker());
        this.checkerPool.put(NEGATIVE_OR_ZERO_CHECKER, new NegativeOrZeroChecker());
        this.checkerPool.put(POSITIVE_OR_ZERO_CHECKER, new PositiveOrZeroChecker());
        this.checkerPool.put(NOT_BLANK_CHECKER, new NotBlankChecker());
        this.checkerPool.put(NOT_EMPTY_CHECKER, new NotEmptyChecker());
        this.checkerPool.put(NOT_NULL_CHECKER, new NotNullChecker());
        this.checkerPool.put(NULL_CHECKER, new NullChecker());
        this.checkerPool.put(REGEX_CHECKER, new RegexChecker());
    }
    public static ConstraintCheckerPool getInstance() {
        return constraintsCheckerPool;
    }

    public ConstraintChecker getChecker(CheckerType checkerType) {
        if (checkerPool.containsKey(checkerType)) return checkerPool.get(checkerType);
        return null;
    }
}
