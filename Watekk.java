import java.util.ArrayList;

public class Watekk
{
    public static void main(String[] args)
    {
    }
}

class Calka {
    double calka(double x) {
        return (Math.sqrt(Math.pow(x, 2) + 0.5) / (1.2 + (Math.sqrt(0.8 * Math.pow(x, 2) + 1.1))));
    }

    public static void main(String[] args) {
        ArrayList<Thread> lista = new ArrayList<>();
        lista.add(new Trapez(0.3, 0.6));
        lista.add(new Trapez(0.6, 0.9));
        lista.add(new Trapez(0.9, 1.2));
        lista.add(new Trapez(1.2, 1.5));
        lista.add(new Simpson(0.3, 0.6));
        lista.add(new Simpson(0.6, 0.9));
        lista.add(new Simpson(0.9, 1.2));
        lista.add(new Simpson(1.2, 1.5));
        lista.add(new Gauss(0.3, 0.6));
        lista.add(new Gauss(0.6, 0.9));
        lista.add(new Gauss(0.9, 1.2));
        lista.add(new Gauss(1.2, 1.5));
        int sumat = 0;
        int sumas = 0;
        int sumag = 0;
        for (Thread t : lista) {
            if (t instanceof Trapez) {
                t.run();
                sumat+=((Trapez) t).wynik;
            }
            if (t instanceof Simpson) {
                t.run();
                sumas+=((Simpson) t).wynik2;
            }
            if (t instanceof Gauss){
                t.run();
                sumag+=((Gauss) t).wynik;
            }
        }
        System.out.println("SUMA TRAPEZ:"+sumat);
        System.out.println("SUMA SIMPSON:"+sumas);
        System.out.println("SUMA GAUSS:"+sumag);
        System.out.println();
//        try
//        {
//            t1.join();
//            t2.join();
//            s1.join();
//            s2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(t1.wynik+t2.wynik);
//        System.out.println(s1.wynik2+s2.wynik2);
//    }
    }

    static class Trapez extends Thread {
        //double xp = 0.3, xk = 1.5, wynik = 0.0, n = 10;
        double xp, xk, wynik, n = 10;


        Trapez(double axp, double axk) {
            xp = axp;
            xk = axk;
        }

        @Override
        public void run() {
            Calka obiekt = new Calka();

            double h = (xk - xp) / n;
            double[] tab = new double[100000];
            for (int i = 0; i < n; i++) {
                tab[i] = xp + (i * h);
            }
            for (int i = 0; i < n - 1; i++) {
                wynik += (obiekt.calka(tab[i]) + obiekt.calka(tab[i + 1]));
            }
            wynik *= h / 2;
            System.out.println("Trapez:");
            System.out.println(wynik);
        }
        public String toString()
        {
            return wynik+"";
        }
    }

    static class Simpson extends Thread {
        //double xp = 0.3, xk = 1.5, n = 10;
        double xp, xk, n = 10, wynik2;

        Simpson(double axp, double axk) {
            xp = axp;
            xk = axk;
        }

        @Override
        public void run() {
            Calka obiekt2 = new Calka();

            double h = (xk - xp) / n;
            double wyniktemp = 0.0;
            double x;
            for (int i = 1; i < n + 1; i++) {
                x = xp + (i * h);
                wyniktemp += obiekt2.calka(x - h / 2);
                if (i < n) {
                    wynik2 += obiekt2.calka(x);
                }
            }

            wynik2 = h / 6 * (obiekt2.calka(xp) + obiekt2.calka(xk) + (2 * wynik2) + (4 * wyniktemp));
            System.out.println("Simpson:");
            System.out.println(wynik2);

        }
        public String toString()
        {
            return wynik2+"";
        }
    }
    static class Gauss extends Thread
    {
        double a,b,wynik = 0;
        Gauss(double axp, double axk) {
            a = axp;
            b = axk;
        }
        public void run()
        {
            Calka obiekt = new Calka();
            double suma = 0;
//granice od do
            double[] waga2 = { 1.0000000000000000, 1.0000000000000000 };
            double[] wezel2 = { -0.5773502691896257, 0.5773502691896257 };
            for (int i = 0; i < waga2.length; i++)
            {
                wynik += waga2[i] * obiekt.calka((b - a) / 2.0 * wezel2[i] + (b + a) / 2.0);
            }
            suma = wynik * ((b - a) / 2.0);
            System.out.println("Kwadratura gaussa-legendre'a dla n = 2 wynosi: ");
            System.out.println(suma);
            suma = 0;
            wynik = 0;
//============================================================================
            double[] waga3 = { 0.8888888888888888, 0.5555555555555556, 0.5555555555555556 };
            double[] wezel3 = { 0.0000000000000000, -0.7745966692414834, 0.7745966692414834 };
            for (int i = 0; i < waga3.length; i++)
            {
                wynik += waga3[i] * obiekt.calka((b - a) / 2.0 * wezel3[i] + (b + a) / 2.0);
            }
            suma = wynik * ((b - a) / 2.0);
            System.out.println("Kwadratura gaussa-legendre'a dla n = 3 wynosi: ");
            System.out.println(suma);
            suma = 0;
            wynik = 0;
//============================================================================
            double[] waga4 = { 0.6521451548625461, 0.6521451548625461, 0.3478548451374538,
                    0.3478548451374538 };
            double[] wezel4 = { -0.3399810435848563, 0.3399810435848563, -0.8611363115940526,
                    0.8611363115940526 };
            for (int i = 0; i < waga4.length; i++)
            {
                wynik += waga4[i] * obiekt.calka((b - a) / 2.0 * wezel4[i] + (b + a) / 2.0);
            }
            suma = wynik * ((b - a) / 2.0);
            System.out.println("Kwadratura gaussa-legendre'a dla n = 4 wynosi: ");
            System.out.println(suma);
            suma = 0;
            wynik = 0;
//=============================================================================
            double[] waga5 = { 0.5688888888888889, 0.4786286704993665, 0.4786286704993665,
                    0.2369268850561891, 0.2369268850561891 };
            double[] wezel5 = { 0.0000000000000000, -0.5384693101056831, 0.5384693101056831, -
                    0.9061798459386640, 0.9061798459386640 };
            for (int i = 0; i < waga5.length; i++)
            {
                wynik += waga5[i] * obiekt.calka((b - a) / 2.0 * wezel5[i] + (b + a) / 2.0);
            }
            suma = wynik * ((b - a) / 2.0);
            System.out.println("Kwadratura gaussa-legendre'a dla n = 5 wynosi: ");
            System.out.println(suma);
            suma = 0;
            wynik = 0;
//==============================================================================
            double[] waga6 = { 0.3607615730481386, 0.3607615730481386, 0.4679139345726910,
                    0.4679139345726910, 0.1713244923791704, 0.1713244923791704 };
            double[] wezel6 = { 0.6612093864662645, -0.6612093864662645, -0.2386191860831969,
                    0.2386191860831969, -0.9324695142031521, 0.9324695142031521 };
            for (int i = 0; i < waga6.length; i++)
            {

                wynik += waga6[i] * obiekt.calka((b - a) / 2.0 * wezel6[i] + (b + a) / 2.0);
            }
            suma = wynik * ((b - a) / 2.0);
            System.out.println("Kwadratura gaussa-legendre'a dla n = 6 wynosi: ");
            System.out.println(suma);
            suma = 0;
            wynik = 0;
//==============================================================================
            double[] waga7 = { 0.4179591836734694, 0.3818300505051189, 0.3818300505051189,
                    0.2797053914892766, 0.2797053914892766, 0.1294849661688697, 0.1294849661688697 };
            double[] wezel7 = { 0.0000000000000000, 0.4058451513773972, -0.4058451513773972, -
                    0.7415311855993945, 0.7415311855993945, -0.9491079123427585, 0.9491079123427585 };
            for (int i = 0; i < waga5.length; i++)
            {
                wynik += waga5[i] * obiekt.calka((b - a) / 2.0 * wezel5[i] + (b + a) / 2.0);
            }
            suma = wynik * ((b - a) / 2.0);
            System.out.println("Kwadratura gaussa-legendre'a dla n = 7 wynosi: ");
            System.out.println(suma);
            suma = 0;
            wynik = 0;
//==============================================================================
            double[] waga8 = { 0.3626837833783620, 0.3626837833783620, 0.3137066458778873,
                    0.3137066458778873, 0.2223810344533745, 0.2223810344533745, 0.1012285362903763, 0.1012285362903763 };
            double[] wezel8 = { -0.1834346424956498, 0.1834346424956498, -0.5255324099163290,
                    0.5255324099163290, -0.7966664774136267, 0.7966664774136267, -0.9602898564975363, 0.9602898564975363 };
            for (int i = 0; i < waga6.length; i++)
            {
                wynik += waga6[i] * obiekt.calka((b - a) / 2.0 * wezel6[i] + (b + a) / 2.0);
            }
            suma = wynik * ((b - a) / 2.0);
            System.out.println("Kwadratura gaussa-legendre'a dla n = 8 wynosi: ");
            System.out.println(suma);
            suma = 0;
            wynik = 0;
        }
    }
}

