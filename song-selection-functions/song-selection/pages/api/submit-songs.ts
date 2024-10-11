import type {NextApiRequest, NextApiResponse} from "next";

export default function handler(
  req: NextApiRequest,
  res: NextApiResponse,
) {
  if (req.method !== 'POST') {
    return res.status(405).json({error: "Methode niet toegestaan"});
  }

  const {date, songs, motivation} = req.body;

  if (!date || !songs || songs.length === 0 || !motivation) {
    return res.status(400).json({error: "Ongeldige invoer"});
  }

  try {
    // You would save the data to a database here

    res.status(200).json({message: "Liedkeuzes succesvol ingediend"});
  } catch (error) {
    res.status(500).json({error: "Interne serverfout"});
  }
}
