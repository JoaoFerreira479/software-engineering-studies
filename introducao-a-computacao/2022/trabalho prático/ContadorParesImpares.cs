using System;
using System.Collections.Generic;

namespace ContadorParesImparesApp
{
    public readonly struct ResultadoContagem
    {
        public int Pares { get; }
        public int Impares { get; }

        public ResultadoContagem(int pares, int impares)
        {
            Pares = pares;
            Impares = impares;
        }
    }

    public static class ContadorParesImpares
    {
        public static ResultadoContagem Contar(IReadOnlyList<int> numeros)
        {
            if (numeros == null)
                throw new ArgumentNullException(nameof(numeros));

            int pares = 0, impares = 0;
            for (int i = 0; i < numeros.Count; i++)
            {
                if (numeros[i] % 2 == 0)
                    pares++;
                else
                    impares++;
            }
            return new ResultadoContagem(pares, impares);
        }

        public static string FormatarResultado(ResultadoContagem r)
        {
            return "\n===== Resultado =====\n" +
                   $"Quantidade de números pares: {r.Pares}\n" +
                   $"Quantidade de números ímpares: {r.Impares}";
        }
    }

    public static class Program
    {
        private const string MensagemErroEntrada = "Erro: entrada inválida. Informe um número inteiro.";
        private const string MensagemErroQuantidade = "Erro: a quantidade deve ser maior que zero.";

        public static int Main()
        {
            if (!TryLerQuantidade(out int quantidade))
            {
                Console.Error.WriteLine(MensagemErroEntrada);
                return 1;
            }
            if (quantidade <= 0)
            {
                Console.Error.WriteLine(MensagemErroQuantidade);
                return 1;
            }

            if (!TryLerNumeros(quantidade, out List<int>? numeros))
            {
                Console.Error.WriteLine(MensagemErroEntrada);
                return 1;
            }

            ResultadoContagem resultado = ContadorParesImpares.Contar(numeros);
            Console.WriteLine(ContadorParesImpares.FormatarResultado(resultado));
            return 0;
        }

        private static bool TryLerQuantidade(out int quantidade)
        {
            quantidade = 0;
            string? linha = Console.ReadLine();
            return int.TryParse(linha, out quantidade);
        }

        private static bool TryLerNumeros(int quantidade, out List<int>? numeros)
        {
            numeros = new List<int>(quantidade);
            for (int i = 0; i < quantidade; i++)
            {
                Console.Write($"Digite o {i + 1}º número: ");
                string? linha = Console.ReadLine();
                if (linha == null || !int.TryParse(linha, out int valor))
                {
                    numeros = null;
                    return false;
                }
                numeros.Add(valor);
            }
            return true;
        }
    }
}
