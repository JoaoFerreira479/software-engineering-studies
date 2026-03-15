using System;

namespace IntroComp.Infrastructure
{
    public static class ConsoleIO
    {
        public static bool TryLerQuantidade(out int quantidade)
        {
            quantidade = 0;
            string? linha = Console.ReadLine();
            return int.TryParse(linha, out quantidade);
        }

        public static bool TryLerNumeros(int quantidade, out System.Collections.Generic.List<int>? numeros)
        {
            numeros = new System.Collections.Generic.List<int>(quantidade);
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

        public static void EscreverErro(string mensagem)
        {
            Console.Error.WriteLine(mensagem);
        }
    }
}
