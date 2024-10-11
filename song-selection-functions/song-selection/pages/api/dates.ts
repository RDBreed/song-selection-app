import type { NextApiRequest, NextApiResponse } from "next";

const availableDates = ["2024-10-03", "2024-10-10", "2024-10-17"];

export default function handler(
  req: NextApiRequest,
  res: NextApiResponse,
) {  try {
    res.status(200).json(availableDates);
  } catch (error) {
    res.status(500).json({ error: "Interne serverfout" });
  }
}
