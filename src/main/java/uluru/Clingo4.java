package uluru;

import net.sf.tweety.lp.asp.parser.ASPParser;
import net.sf.tweety.lp.asp.parser.InstantiateVisitor;
import net.sf.tweety.lp.asp.solver.Clingo;
import net.sf.tweety.lp.asp.solver.SolverException;
import net.sf.tweety.lp.asp.util.AnswerSetList;

import java.io.StringReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by philip on 6/10/17.
 */
public class Clingo4 extends Clingo {
    public Clingo4(String path2clingo) {
        super(path2clingo);
    }

    protected AnswerSetList buildASL(List<String> output) {
        output.remove(0);
        output.remove(0);
        output.remove(0);
        output.remove(0);

        String toParse = "";
        String as;
        for(Iterator var3 = output.iterator(); var3.hasNext(); toParse = toParse + as) {
            String line = (String)var3.next();
            if(line.startsWith("SATISFIABLE") || (line.startsWith("Optimization"))) {
                if(line.startsWith("Optimization")) {
                    System.out.println(line);
                }
                break;
            }
            as = line.replace(' ', ',');
            as = "{" + as + "}";
            as = as.replace(",}", "}");

        }

        //System.out.println(output);
        try {
            ASPParser ep = new ASPParser(new StringReader(toParse));
            InstantiateVisitor visitior = new InstantiateVisitor();
            return visitior.visit(ep.AnswerSetList(), (Object)null);
        } catch (Exception var4) {
            System.err.println("clingo: error parsing answer set!");
            var4.printStackTrace();
            return null;
        }
    }

    public AnswerSetList computeModels(List<String> files, int maxModels) throws SolverException {
        this.checkSolver(this.path2clingo);

        try {
            LinkedList<String> f2 = new LinkedList(files);
            f2.addFirst("-q1,1");
            f2.addFirst(this.path2clingo);
            this.ai.executeProgram(f2, (String) null);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.checkErrors();
        return this.buildASL(this.ai.getOutput());
    }
}
