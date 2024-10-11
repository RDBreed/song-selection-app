import type { NextApiRequest, NextApiResponse } from "next";

const songs = [
  { number: 1, title: "Amazing Grace", bundle: "Hymns" },
  { number: 2, title: "Blessed Assurance", bundle: "Hymns" },
  // Add more song objects here
];

export default function handler(
  req: NextApiRequest,
  res: NextApiResponse,
) {   try {
    res.status(200).json(songs);
  } catch (error) {
    res.status(500).json({ error: "Interne serverfout" });
  }
}
