const SONG_API_URL: string | undefined = process.env["SONG_API_URL"]!

export function getSongApiUrl(): string {
  if (!SONG_API_URL || SONG_API_URL.length === 0) {
    throw new Error('The environment variable SONG_API_URL is not set.')
  }

  return SONG_API_URL
}
