namespace IntroComp.Domain
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
}
