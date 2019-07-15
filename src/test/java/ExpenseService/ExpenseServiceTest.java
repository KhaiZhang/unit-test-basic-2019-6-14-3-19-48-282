package ExpenseService;

import ExpenseService.Exception.UnexpectedProjectTypeException;
import ExpenseService.Expense.ExpenseType;
import ExpenseService.Project.Project;
import ExpenseService.Project.ProjectType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertSame;

class ExpenseServiceTest {
    @Test
    void should_return_internal_expense_type_if_project_is_internal() throws UnexpectedProjectTypeException {
        // given
        Project internalProject = new Project(ProjectType.INTERNAL,"internal");
        // when
        ExpenseType expenseCodeByProjectTypeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(internalProject);
        // then
        assertSame(ExpenseType.INTERNAL_PROJECT_EXPENSE,expenseCodeByProjectTypeAndName);

    }

    @Test
    void should_return_expense_type_A_if_project_is_external_and_name_is_project_A() throws UnexpectedProjectTypeException {
        // given
        Project expenseProjectA = new Project(ProjectType.EXTERNAL,"Project A");
        // when
        ExpenseType expenseCodeByProjectTypeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(expenseProjectA);
        // then
        assertSame(ExpenseType.EXPENSE_TYPE_A,expenseCodeByProjectTypeAndName);
    }

    @Test
    void should_return_expense_type_B_if_project_is_external_and_name_is_project_B() throws UnexpectedProjectTypeException {
        // given
        Project expenseProjectB = new Project(ProjectType.EXTERNAL,"Project B");
        // when
        ExpenseType expenseCodeByProjectTypeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(expenseProjectB);
        // then
        assertSame(ExpenseType.EXPENSE_TYPE_B,expenseCodeByProjectTypeAndName);
    }

    @Test
    void should_return_other_expense_type_if_project_is_external_and_has_other_name() throws UnexpectedProjectTypeException {
        // given
        Project otherProject = new Project(ProjectType.EXTERNAL,"Other Project");
        // when
        ExpenseType expenseCodeByProjectTypeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(otherProject);
        // then
        assertSame(ExpenseType.OTHER_EXPENSE,expenseCodeByProjectTypeAndName);
    }

    @Test
    void should_throw_unexpected_project_exception_if_project_is_invalid() {
        // given
        Project otherProject = new Project(ProjectType.UNEXPECTED_PROJECT_TYPE,"Invalid Project");
        // when
        ExpenseType expenseCodeByProjectTypeAndName = null;
        String errorMessage="";
        try {
            expenseCodeByProjectTypeAndName = ExpenseService.getExpenseCodeByProjectTypeAndName(otherProject);
        } catch (UnexpectedProjectTypeException e) {
            errorMessage=e.getMessage();
        }
        // then
        Assertions.assertSame("You enter invalid project type",errorMessage);
    }
}