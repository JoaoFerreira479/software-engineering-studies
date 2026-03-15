using System;
using IntroComp.Domain;
using IntroComp.Infrastructure;

namespace IntroComp.Application
{
    public static class Program
    {
        private const string MensagemErroEntrada = "Erro: entrada inválida. Informe um número inteiro.";
        private const string MensagemErroQuantidade = "Erro: a quantidade deve ser maior que zero.";

        public static int Main()
        {
            if (!ConsoleIO.TryLerQuantidade(out int quantidade))
            {
                ConsoleIO.EscreverErro(MensagemErroEntrada);
                return 1;
            }
            if (quantidade <= 0)
            {
                ConsoleIO.EscreverErro(MensagemErroQuantidade);
                return 1;
            }

            if (!ConsoleIO.TryLerNumeros(quantidade, out var numeros) || numeros == null)
            {
                ConsoleIO.EscreverErro(MensagemErroEntrada);
                return 1;
            }

            ResultadoContagem resultado = ContadorParesImpares.Contar(numeros);
            Console.WriteLine(ContadorParesImpares.FormatarResultado(resultado));
            return 0;
        }
    }
}
