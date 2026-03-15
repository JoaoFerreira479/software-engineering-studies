using System.Collections.Generic;

namespace IntroComp.Domain
{
    public static class ContadorParesImpares
    {
        public static ResultadoContagem Contar(IReadOnlyList<int> numeros)
        {
            if (numeros == null)
                throw new System.ArgumentNullException(nameof(numeros));

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
}
