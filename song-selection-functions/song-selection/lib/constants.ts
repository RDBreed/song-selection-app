const XATA_BRANCH: string | undefined = process.env["XATA_BRANCH"]!

export function getXataBranch(): string {
  if (!XATA_BRANCH || XATA_BRANCH.length === 0) {
    throw new Error('The environment variable SONG_API_URL is not set.')
  }

  return XATA_BRANCH
}
